package it.mazurek.showcat.model.DTO;

public class CatDTO {

    private String cat;
    private Integer points;

    public CatDTO(String cat, Integer points) {
        this.cat = cat;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public String getCat() {
        return cat;
    }
}
