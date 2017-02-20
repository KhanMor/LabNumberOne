package com.kharkhanov;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import parser.Parser;
import parser.ParserRunnable;
import wordsjob.UniqueWordsWrapper;
import wordsjob.WordValidator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mordr on 15.02.2017.
 */
public class MainAdditional {
    public static volatile boolean stop = false;
    private static final Logger logger = Logger.getLogger(Main.class);
    static  {
        DOMConfigurator.configure("src/resources/log4j.xml");
    }

    public static void main(String[] args) {
        logger.trace("Program started...");

        UniqueWordsWrapper uniqueWords = new UniqueWordsWrapper();
        WordValidator wordValidator = new WordValidator(uniqueWords);
        ExecutorService executorService = Executors.newFixedThreadPool(args.length);

        Parser parser = new Parser(wordValidator);
        for (String resource:args) {
            executorService.submit(new ParserRunnable(resource, parser));
        }
    }
}
