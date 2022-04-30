package com.flashcard;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface FlashcardRepository {

    List<Flashcard> findAll();

    List<Flashcard> findAllById(int id);

    Flashcard save(Flashcard entity);

    void deleteById(int id);
}
