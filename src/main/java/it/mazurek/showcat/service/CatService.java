package it.mazurek.showcat.service;

import it.mazurek.showcat.model.Breed;
import it.mazurek.showcat.model.Cat;
import it.mazurek.showcat.repository.CatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    public Optional<Cat> findById(Long id) {
        return catRepository.findById(id);
    }

    @Transactional
    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    public Optional<Cat> findCatByTicketId(Long id) {
        return catRepository.findCatByTicketsIsContaining(id);
    }

    public List<Cat> findCatsByBreed(Breed breed) {
        return catRepository.findCatsByBreed(breed);
    }
}
