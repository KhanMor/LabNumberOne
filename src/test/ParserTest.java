package test;

import com.kharkhanov.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mordr on 13.02.2017.
 * Тестировние парсера
 */
class ParserTest {
    private Parser parser;

    @BeforeEach
    void init() {
        parser = new Parser();
        assertNotNull(parser);
        Main.uniqueWords.clear();
        assertTrue(Main.uniqueWords.size() == 0);
    }

    @Test
    void parseResource() {
        parser.parseResource("Files/Правильный.файл.txt");
        assertTrue(Main.uniqueWords.size() > 1 );
    }

}