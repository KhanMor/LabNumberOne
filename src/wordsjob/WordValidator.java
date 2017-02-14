package wordsjob;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Mordr on 10.02.2017.
 * Валидация
 */
public class WordValidator {
    private final UniqueWordsWrapper uniqueWords;
    private static final Logger logger = Logger.getLogger(WordValidator.class);
    static  {
        DOMConfigurator.configure("src/resources/log4j.xml");
    }

    public WordValidator(UniqueWordsWrapper uniqueWords) {
        this.uniqueWords = uniqueWords;
    }

    /**
     * Проверяет слово на уникальность, если оно уникально, то
     * проверяет содержатся ли в нем только кириллица, знаки препинания и цифры
     * если слово проходит все проверки, добавляет слово к списку уникальных слов
     * @param word слово, которое нужно проверить
     * @param resource ресурс, где было найдено слово
     * @return true если слово уникально и не содерджит инностранных символов,
     * иначе false
     */

    public boolean addValidWord(String word, String resource) {
        synchronized (uniqueWords) {
            if (!uniqueWords.isUniqueWord(word)) {
                logger.error("Not unique word " + word + " found in " + resource);
                return false;
            } else {
                if (word.matches("[а-яА-ЯёЁ\\d\\p{Punct}]*")) {
                    uniqueWords.addUniqueWord(word, resource);
                    return true;
                } else {
                    logger.error("Foreign word " + word + " found in " + resource);
                    return false;
                }
            }
        }
    }
}
