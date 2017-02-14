package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wordsjob.UniqueWordsWrapper;
import wordsjob.WordValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mordr on 13.02.2017.
 * Тестирования валидатора
 */
class WordValidatorTest {
    private WordValidator wordValidator;

    @BeforeEach
    void setUp() {
        UniqueWordsWrapper uniqueWordsWrapper = new UniqueWordsWrapper();
        WordValidator wordValidator = new WordValidator(uniqueWordsWrapper);
        assertNotNull(wordValidator);
        this.wordValidator = wordValidator;
    }

    @Test
    void addValidWord() {
        assertTrue(wordValidator.addValidWord("правильное.слово", "ValidResourceName"));
    }

    @Test
    void addDuplicateWord() {
        wordValidator.addValidWord("дубль", "DuplicateResourceName");
        assertFalse(wordValidator.addValidWord("дубль", "ResourceName"));
    }

    @Test
    void addForeignWord() {
        assertFalse(wordValidator.addValidWord("foreign.word", "ForeignResourceName"));
    }
}