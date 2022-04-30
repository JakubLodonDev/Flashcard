package com.flashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlFlashcardRepository extends FlashcardRepository, JpaRepository<Flashcard, Integer> {

}
