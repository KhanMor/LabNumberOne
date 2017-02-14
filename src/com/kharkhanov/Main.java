package com.kharkhanov;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import parser.Parser;
import parser.ParserRunnable;
import wordsjob.UniqueWordsWrapper;
import wordsjob.WordValidator;

import java.util.*;

public class Main {
    public static volatile boolean stop = false;
    private static final Logger logger = Logger.getLogger(Main.class);
    static  {
        DOMConfigurator.configure("src/resources/log4j.xml");
    }

    public static void main(String[] args) {
        logger.trace("Program started...");
        List<Thread> threads = new ArrayList<>();
        UniqueWordsWrapper uniqueWords = new UniqueWordsWrapper();
        WordValidator wordValidator = new WordValidator(uniqueWords);
        Parser parser = new Parser(wordValidator);

        for (String resource:args) {
            Thread thread = new Thread(new ParserRunnable(resource, parser));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.trace("Program done.");
    }
}
