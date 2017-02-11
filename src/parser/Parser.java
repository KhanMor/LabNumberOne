package parser;

import com.kharkhanov.Main;

import java.io.*;

/**
 * Created by Mordr on 10.02.2017.
 */
public class Parser {

    /**
     * @param resource путь к текстовому файлу с текстом,
     *                 если файла нет то работа программы прекращается
     **/
    public void parseResource(String resource) {
        try (
                InputStream inputStream=new FileInputStream(new File(resource));
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader)
        ) {
            String line;
            WordValidator wordValidator = new WordValidator();
            while (!Main.stop && (line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    boolean wordIsValid = wordValidator.isValidWord(word, resource);
                    if(wordIsValid && !Main.stop) {
                        wordValidator.addValidWord(word, resource);
                    }else{
                        Main.stop = true;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Main.stop = true;
            e.printStackTrace();
        } catch (IOException e) {
            Main.stop = true;
            e.printStackTrace();
        }
    }
}
