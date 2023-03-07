package it.mazurek.showcat.model.DTO;

import java.util.List;

public class GroupResultDTO {

    private String breed;
    private List<CatDTO> first;
    private List<CatDTO> second;
    private List<CatDTO> third;

    public GroupResultDTO(String breed, List<CatDTO> first, List<CatDTO> second, List<CatDTO> third) {
        this.breed = breed;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public String getBreed() {
        return breed;
    }

    public List<CatDTO> getFirst() {
        return first;
    }

    public List<CatDTO> getSecond() {
        return second;
    }

    public List<CatDTO> getThird() {
        return third;
    }
}
