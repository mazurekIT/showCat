package it.mazurek.showcat.model.DTO;

import java.util.List;

public class ResultDTO {
    private List<GroupResultDTO> bestInBreeds;
    private List<CatDTO> bestFromPublic;

    public ResultDTO(List<GroupResultDTO> bestInBreeds, List<CatDTO> bestFromPublic) {
        this.bestInBreeds = bestInBreeds;
        this.bestFromPublic = bestFromPublic;
    }

    public List<CatDTO> getBestFromPublic() {
        return bestFromPublic;
    }

    public List<GroupResultDTO> getBestInBreeds() {
        return bestInBreeds;
    }
}
