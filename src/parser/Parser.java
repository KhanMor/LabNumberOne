package parser;

import com.kharkhanov.Main;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import wordsjob.WordValidator;

import java.io.*;
import java.net.URL;

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
     * Возвращает поток ввода для ресурса в зависимости от того на что он ссылается
     * (URL или файл)
     * @param resource путь к ресурсу
     * @return возвращает поток ввода
     * @throws IOException ошибка ввода вывода если ресурс не неайден
     */
    private InputStream getInputStream(String resource) throws IOException {
        InputStream inputStream;
        if(resource.toLowerCase().contains("http://")
                || resource.toLowerCase().contains("https://")) {
            inputStream = new URL(resource).openStream();
        } else {
            inputStream = new FileInputStream(new File(resource));
        }
        return inputStream;
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
                    InputStream inputStream = getInputStream(resource);
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
