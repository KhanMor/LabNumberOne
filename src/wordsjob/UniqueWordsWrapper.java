package wordsjob;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mordr on 14.02.2017.
 * Класс-обертка для хранения коллекции уникальных слов
 * и работы с этой коллекцией
 */
public class UniqueWordsWrapper {
    private final Set<String> uniqueWords;
    private static final Logger logger = Logger.getLogger(UniqueWordsWrapper.class);

    public UniqueWordsWrapper(){
        uniqueWords = new HashSet<>();
    }

    /**
     * Проверяет, есть ли слово в коллекции
     * @param word слово, которое необходимо проверить
     * @return возвращает true если такого слова еще нет в коллекции
     */
    boolean isUniqueWord(String word) {
        return !uniqueWords.contains(word);
    }

    /**
     * Добавляет слово к коллекции
     * @param word слово, которое необходимо добавить
     * @param resource наименование ресурса, в котором находится это слово
     */
    void addUniqueWord(String word, String resource) {
        uniqueWords.add(word);
        logger.trace("found word " + word + " in " + resource);
    }
}
