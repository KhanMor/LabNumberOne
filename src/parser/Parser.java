package parser;

import com.kharkhanov.Main;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import wordsjob.WordValidator;

import java.io.*;

/**
 * Created by Mordr on 10.02.2017.
 * Парсер текста
 */
public class Parser {
    private final WordValidator wordValidator;
    private static final Logger logger = Logger.getLogger(Main.class);
    static  {
        DOMConfigurator.configure("src/resources/log4j.xml");
    }

    public Parser(WordValidator wordValidator) {
        this.wordValidator = wordValidator;
    }

    /**
     * Считывает тектовый файл по-строчно, разбивает строку на слова,
     * затем проверяет полученные слова, если слово удовлетворяет всем условиям
     * добавляет его в коллекцию уникальных слов
     * @param resource путь к текстовому файлу с текстом,
     *                 если файла нет то работа программы прекращается
     **/
    void parseResource(String resource) {
        if(resource != null) {
            try (
                    InputStream inputStream = new FileInputStream(new File(resource));
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
            ) {
                String line;
                while (!Main.stop && (line = bufferedReader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        boolean validWordAdded = wordValidator.addValidWord(word, resource);
                        if (!validWordAdded || Main.stop) {
                            Main.stop = true;
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                Main.stop = true;
                logger.error("Exception occurred " + e.getMessage());
            }
        }
    }
}
