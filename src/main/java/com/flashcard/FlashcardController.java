package com.flashcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class FlashcardController {
    private final FlashcardRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(FlashcardController.class);


    public FlashcardController(FlashcardRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/flashcard")
    ResponseEntity<Flashcard> createFlashcard(@RequestBody @Valid Flashcard newFlashcard) {
        Flashcard flashcard = repository.save(newFlashcard);
        logger.warn("Create new flashcard");
        return ResponseEntity.created(URI.create("/" + flashcard.getId())).body(flashcard);
    }

    @GetMapping(value = "/flashcards", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Flashcard>> readAllFlashcard() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/flashcards")
    ResponseEntity<List<Flashcard>> readAllFlashcards(Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping(value = "/flashcard/{id}")
    ResponseEntity<Flashcard> readFlashcard(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/flashcard/{id}")
    ResponseEntity<?> updateFlashcard(@PathVariable int id, @RequestBody Flashcard toUpdate) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/flashcard/{id}")
    ResponseEntity<?> deleteFlashcard(@PathVariable(value = "id") Flashcard flashcard) {
        if(!repository.existsById(flashcard.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(flashcard.getId());
        return ResponseEntity.noContent().build();
    }
}
