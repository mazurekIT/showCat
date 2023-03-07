package it.mazurek.showcat.controller;

import it.mazurek.showcat.model.DTO.ResultDTO;
import it.mazurek.showcat.model.DTO.ViewDTO;
import it.mazurek.showcat.service.ViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class ViewController {
    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ViewDTO>> getCats() {
        return ResponseEntity.ok(viewService.getAllCatsGroupByBreed());
    }

    @PatchMapping("/{catId}/{ticketId}/vote")
    @ResponseStatus(HttpStatus.OK)
    public void ticketVote(
            @PathVariable Long catId,
            @PathVariable Long ticketId
    ) {
        viewService.peopleVoteAtCat(ticketId, catId);
    }

    @PatchMapping("/{catId}/{judgeId}/vote/{points}")
    @ResponseStatus(HttpStatus.OK)
    public void ticketVote(
            @PathVariable Long catId,
            @PathVariable Long judgeId,
            @PathVariable @Min(value = 0) @Max(value = 10) Integer points
    ) {
        viewService.judgeVoteAtCat(judgeId, catId, points);
    }

    @GetMapping("/results")
    public ResponseEntity<ResultDTO> getResults() {
        return ResponseEntity.ok(viewService.getResults());
    }

}
