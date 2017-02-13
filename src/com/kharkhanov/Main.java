package com.kharkhanov;

import parser.ParserRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static volatile boolean stop = false;
    public static volatile Set<String> uniqueWords = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Program started...");
        List<Thread> threads = new ArrayList<>();
        for (String resource:args) {
            Thread thread = new Thread(new ParserRunnable(resource));
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

        System.out.println("Program done.");
    }
}
