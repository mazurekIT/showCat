package it.mazurek.showcat.service;

import it.mazurek.showcat.model.Breed;
import it.mazurek.showcat.model.Cat;
import it.mazurek.showcat.repository.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {

    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatService catService;

    @Test
    void testFindAll() {
        List<Cat> expectedCats = Arrays.asList(new Cat(), new Cat());
        when(catRepository.findAll()).thenReturn(expectedCats);

        List<Cat> actualCats = catService.findAll();

        assertEquals(expectedCats, actualCats);
        assertEquals(2, actualCats.size());
        verify(catRepository, times(1)).findAll();

    }

    @Test
    void testFindById() {
        Long catId = 1L;
        Cat expectedCat = new Cat();
        when(catRepository.findById(catId)).thenReturn(Optional.of(expectedCat));

        Optional<Cat> actualCat = catService.findById(catId);

        assertEquals(expectedCat, actualCat.get());
        verify(catRepository, times(1)).findById(catId);
    }

    @Test
    void testSave() {
        Cat catToSave = new Cat();
        when(catRepository.save(catToSave)).thenReturn(catToSave);

        Cat savedCat = catService.save(catToSave);

        assertEquals(catToSave, savedCat);
        verify(catRepository, times(1)).save(catToSave);
    }

    @Test
    void testFindCatByTicketId() {
        Long ticketId = 1L;
        Cat expectedCat = new Cat();
        when(catRepository.findCatByTicketsIsContaining(ticketId)).thenReturn(Optional.of(expectedCat));

        Optional<Cat> actualCat = catService.findCatByTicketId(ticketId);

        assertEquals(expectedCat, actualCat.get());
        verify(catRepository, times(1)).findCatByTicketsIsContaining(ticketId);
    }

    @Test
    void testFindCatsByBreed() {
        Breed breed = Breed.SYJAMSKI;
        List<Cat> expectedCats = Arrays.asList(new Cat(), new Cat());
        when(catRepository.findCatsByBreed(breed)).thenReturn(expectedCats);

        List<Cat> actualCats = catService.findCatsByBreed(breed);

        assertEquals(expectedCats, actualCats);
        assertEquals(2, actualCats.size());
        verify(catRepository, times(1)).findCatsByBreed(breed);
    }
}