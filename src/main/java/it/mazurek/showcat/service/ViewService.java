package it.mazurek.showcat.service;

import it.mazurek.showcat.exceptions.*;
import it.mazurek.showcat.model.Breed;
import it.mazurek.showcat.model.Cat;
import it.mazurek.showcat.model.DTO.CatDTO;
import it.mazurek.showcat.model.DTO.GroupResultDTO;
import it.mazurek.showcat.model.DTO.ResultDTO;
import it.mazurek.showcat.model.DTO.ViewDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewService {

    private final CatService catService;

    public ViewService(CatService catService) {
        this.catService = catService;
    }

    public List<ViewDTO> getAllCatsGroupByBreed() {
        return Arrays.stream(Breed.values())
                .map(breed -> new ViewDTO(
                        breed.name(),
                        catService.findCatsByBreed(breed)))
                .toList();

    }

    public ResultDTO getResults() {
        return new ResultDTO(getJudgeBest(), getPublicBest());
    }

    public void peopleVoteAtCat(Long ticketId, Long catId) {
        catService.findCatByTicketId(ticketId).ifPresent(s -> {
            throw new TicketAlreadyVotedException(ticketId);
        });
        catService.findById(catId)
                .ifPresentOrElse(
                        cat -> {
                            cat.getTickets().add(ticketId);
                            catService.save(cat);
                        },
                        () -> {
                            throw new CatNotFoundException(catId);
                        });
    }


    public void judgeVoteAtCat(Long judgeId, Long catId, Integer points) {
        validPoints(points);
        catService.findById(catId)
                .ifPresentOrElse(cat -> {
                            verifyIfJudgeCanVote(judgeId, cat.getJudgePointsMap().keySet());
                            cat.getJudgePointsMap().put(judgeId, points);
                            catService.save(cat);
                        },
                        () -> {
                            throw new CatNotFoundException(catId);
                        });
    }


    private void validPoints(Integer points) {
        if (points < 0 || points > 10) {
            throw new PointsNotValidException();
        }
    }

    private void verifyIfJudgeCanVote(Long judgeId, Set<Long> judges) {
        if (judges.contains(judgeId)) {
            throw new JudgeAlreadyVotedException();
        }
        if (judges.size() == 3) {
            throw new ToManyJudgesException();
        }
    }


    private List<GroupResultDTO> getJudgeBest() {
        return getAllCatsGroupByBreed().stream()
                .map(group -> getCatsOnPodium(group.getBreed(), group.getCats()))
                .toList();
    }

    private List<CatDTO> getPublicBest() {
        List<Cat> cats = catService.findAll();
        Optional<Integer> maxVotes = cats.stream()
                .map(cat -> cat.getTicketsCount())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .findFirst();

        return cats.stream().filter(cat -> cat.getTicketsCount() == maxVotes.get())
                .map(cat -> new CatDTO(cat.getFullName(), cat.getTicketsCount()))
                .toList();
    }


    private GroupResultDTO getCatsOnPodium(String breed, List<Cat> cats) {
        List<CatDTO> second = null;
        List<CatDTO> third = null;
        List<Integer> maxVotes = cats.stream()
                .map(Cat::getPointsFromJudges)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();

        List<CatDTO> first = getCatWithPoints(cats, maxVotes.get(0));

        if (getNumberOfCatsAtAbovePosition(first, null) == 1) {
            second = getCatWithPoints(cats, maxVotes.get(1));
        }

        if (getNumberOfCatsAtAbovePosition(first, second) == 2) {
            third = getCatWithPoints(cats, maxVotes.get(2));
        }
        return new GroupResultDTO(breed, first, second, third);
    }

    private static List<CatDTO> getCatWithPoints(List<Cat> cats, Integer points) {
        return cats.stream().filter(cat -> cat.getPointsFromJudges() == points)
                .map(cat -> new CatDTO(cat.getFullName(), cat.getPointsFromJudges()))
                .toList();
    }

    private long getNumberOfCatsAtAbovePosition(List<CatDTO> first, List<CatDTO> second) {
        return second == null ? first.size() : first.size() + second.size();
    }

}

