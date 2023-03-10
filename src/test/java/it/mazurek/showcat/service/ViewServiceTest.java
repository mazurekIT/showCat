package it.mazurek.showcat.service;

import it.mazurek.showcat.exceptions.*;
import it.mazurek.showcat.model.Breed;
import it.mazurek.showcat.model.Cat;
import it.mazurek.showcat.model.DTO.ViewDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViewServiceTest {

    @Mock
    private CatService catService;

    @InjectMocks
    private ViewService viewService;


    @Nested
    @DisplayName("getAllCatsGroupByBreed")
    class GetAllCatsGroupByBreedTests {

        @Test
        void testGetAllCatsGroupByBreed() {
            // given
            when(catService.findCatsByBreed(any(Breed.class))).thenReturn(Arrays.asList(new Cat(), new Cat()));

            // when
            List<ViewDTO> result = viewService.getAllCatsGroupByBreed();

            // then
            verify(catService, times(Breed.values().length)).findCatsByBreed(any(Breed.class));
            assertEquals(Breed.values().length, result.size());
        }


        @Test
        void testNumberOfCatsInBreedWhenCallGetAllCatsGroupByBreed() {
            Cat cat1 = new Cat();
            cat1.setBreed(Breed.SYBERYJSKI);
            Cat cat2 = new Cat();
            cat2.setBreed(Breed.SYBERYJSKI);
            List<Cat> cats = Arrays.asList(
                    cat1, cat2);
            when(catService.findCatsByBreed(Breed.SYBERYJSKI)).thenReturn(cats);

            List<ViewDTO> result = viewService.getAllCatsGroupByBreed();

            assertEquals(4, result.size());
            assertEquals(Breed.SYBERYJSKI.name(), result.get(0).getBreed());
            assertEquals(2, result.get(0).getCats().size());
        }
    }


    @Nested
    @DisplayName("peopleVoteAtCat")
    class PeopleVoteAtCatTests {
        @Test
        void testPeopleVoteAtCat() {
            Cat cat = new Cat();
            cat.setId(1L);
            cat.setTickets(new HashSet<>());

            Mockito.when(catService.findCatByTicketId(123L)).thenReturn(Optional.empty());
            Mockito.when(catService.findById(1L)).thenReturn(Optional.of(cat));

            viewService.peopleVoteAtCat(123L, 1L);

            assertTrue(cat.getTickets().contains(123L));
            Mockito.verify(catService).save(cat);
        }

        @Test
        void testPeopleVoteAtCat_ticketAlreadyVoted() {
            Cat cat = new Cat();
            cat.setId(1L);
            cat.setTickets(new HashSet<>());
            cat.getTickets().add(123L);

            Mockito.when(catService.findCatByTicketId(123L)).thenReturn(Optional.of(cat));

            assertThrows(TicketAlreadyVotedException.class,
                    () -> viewService.peopleVoteAtCat(123L, 1L));
        }

        @Test
        void testPeopleVoteAtCat_catNotFound() {
            Mockito.when(catService.findCatByTicketId(123L)).thenReturn(Optional.empty());
            Mockito.when(catService.findById(1L)).thenReturn(Optional.empty());

            assertThrows(CatNotFoundException.class,
                    () -> viewService.peopleVoteAtCat(123L, 1L));
        }

    }


    @Nested
    @DisplayName("judgesVoteAtCat")
    class JudgesVoteAtCatTests {
        @Test
        void testJudgeVoteAtCat() {
            Cat cat = new Cat();
            cat.setId(1L);
            Map<Long, Integer> pointsMap = new HashMap<>();
            pointsMap.put(111L, 5);
            cat.setJudgePointsMap(pointsMap);

            Mockito.when(catService.findById(1L)).thenReturn(Optional.of(cat));

            viewService.judgeVoteAtCat(222L, 1L, 8);

            assertEquals(2, cat.getJudgePointsMap().size());
            assertEquals(8, (int) cat.getJudgePointsMap().get(222L));
            Mockito.verify(catService).save(cat);
        }

        @Test
        void testJudgeVoteAtCat_pointsAreToLow() {
            Long catId = 1L;
            Long judgeId = 222L;
            Integer points = -1;


            assertThrows(PointsNotValidException.class,
                    () -> viewService.judgeVoteAtCat(judgeId, catId, points));
        }

        @Test
        void testJudgeVoteAtCat_pointsAreToHigh() {
            Long catId = 1L;
            Long judgeId = 222L;
            Integer points = 11;


            assertThrows(PointsNotValidException.class,
                    () -> viewService.judgeVoteAtCat(judgeId, catId, points));
        }

        @Test
        void testJudgeVoteAtCat_judgeCantVote_toManyJudges() {
            Long judgeId = 222L;
            Integer points = 5;
            Map<Long, Integer> pointsMap = new HashMap<>();
            pointsMap.put(111L, points);
            pointsMap.put(333L, points);
            pointsMap.put(444L, points);

            Cat cat = new Cat();
            cat.setId(1L);
            cat.setJudgePointsMap(pointsMap);

            Mockito.when(catService.findById(1L)).thenReturn(Optional.of(cat));

            assertThrows(ToManyJudgesException.class,
                    () -> viewService.judgeVoteAtCat(judgeId, cat.getId(), points));
        }

        @Test
        void testJudgeVoteAtCat_judgeCantVote_judgeAlreadyVoted() {
            Long judgeId = 222L;
            Integer points = 5;
            Map<Long, Integer> pointsMap = new HashMap<>();
            pointsMap.put(judgeId, points);

            Cat cat = new Cat();
            cat.setId(1L);
            cat.setJudgePointsMap(pointsMap);

            Mockito.when(catService.findById(1L)).thenReturn(Optional.of(cat));

            assertThrows(JudgeAlreadyVotedException.class,
                    () -> viewService.judgeVoteAtCat(judgeId, cat.getId(), points));
        }

        @Test
        void testJudgesVoteAtCat_catNotFound() {
            Mockito.when(catService.findById(1L)).thenReturn(Optional.empty());

            assertThrows(CatNotFoundException.class,
                    () -> viewService.judgeVoteAtCat(222L, 1L, 5));
        }
    }

}

