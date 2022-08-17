DROP TABLE IF EXISTS flashcards;
CREATE TABLE flashcards(
                           id int PRIMARY KEY AUTO_INCREMENT,
                           phrase VARCHAR(100) NOT NULL,
                           translation VARCHAR(100) NOT NULL,
                           done bit
);