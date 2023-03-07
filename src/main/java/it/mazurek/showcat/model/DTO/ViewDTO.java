package it.mazurek.showcat.model.DTO;

import it.mazurek.showcat.model.Cat;

import java.util.List;

public class ViewDTO {

    private String breed;
    private List<Cat> cats;

    public ViewDTO(String breed, List<Cat> cats) {
        this.breed = breed;
        this.cats = cats;
    }

    public String getBreed() {
        return breed;
    }

    public List<Cat> getCats() {
        return cats;
    }

}
