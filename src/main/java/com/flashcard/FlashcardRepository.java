package com.flashcard;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FlashcardRepository {

    List<Flashcard> findAll();
    Page<Flashcard> findAll(Pageable page);

    Optional<Flashcard> findById(Integer id);

    Flashcard save(Flashcard entity);

    void deleteById(Integer id);
}
