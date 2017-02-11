package parser;

import com.kharkhanov.Main;

/**
 * Created by Mordr on 10.02.2017.
 */
public class WordValidator {

    /**
     * Проверяет слово на уникальность, если оно уникально, то
     * проверяет содержатся ли в нем только кириллица, знаки препинания и цифры
     * @param word слово, которое нужно проверить
     * @param resource ресурс, где было найдено слово
     * @return true если слово уникально и не содерджит инностранных символов,
     * иначе false
     */

    public boolean isValidWord(String word, String resource) {
        synchronized (Main.uniqueWords) {
            if (Main.uniqueWords.contains(word)) {
                System.out.println("Not unique word " + word + " found in " + resource);
                return false;
            } else {
                if (word.matches("[а-яА-ЯёЁ\\d\\p{Punct}]*")) {
                    return true;
                } else {
                    System.out.println("Foreign word " + word + " found in " + resource);
                    return false;
                }
            }
        }
    }

    /**
     * Добавляет слово к списку уникальных слов
     * @param word слово, которое будет добавленно к списку уникальных слов
     * @param resource ресурс в котором было найденно уникальное слово
     */
    public void addValidWord(String word, String resource){
        synchronized (Main.uniqueWords) {
            Main.uniqueWords.add(word);
            System.out.println("found word " + word + " in " + resource);
        }
    }
}
