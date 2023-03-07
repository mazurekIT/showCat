package it.mazurek.showcat.repository;

import it.mazurek.showcat.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
 

}
