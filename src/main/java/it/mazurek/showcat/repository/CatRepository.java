package it.mazurek.showcat.repository;

import it.mazurek.showcat.model.Breed;
import it.mazurek.showcat.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findCatByTicketsIsContaining(Long id);

    List<Cat> findCatsByBreed(Breed breed);

}
