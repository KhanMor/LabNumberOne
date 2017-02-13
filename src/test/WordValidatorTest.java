package test;

import com.kharkhanov.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.WordValidator;

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
        WordValidator wordValidator = new WordValidator();
        assertNotNull(wordValidator);
        this.wordValidator = wordValidator;
    }

    @Test
    void isValidWord() {
        assertTrue(wordValidator.isValidWord("правильное.слово", "ResourceName"));

        wordValidator.addValidWord("правильное.слово", "ResourceName");
        wordValidator.addValidWord("правильное.слово", "ResourceName");

        assertFalse(wordValidator.isValidWord("wrong.word", "ResourceName"));
    }

    @Test
    void addValidWord() {
        wordValidator.addValidWord("уникальное.слово", "ResourceName");
        assertTrue(Main.uniqueWords.contains("уникальное.слово"));
    }

}