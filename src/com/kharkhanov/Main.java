package com.kharkhanov;

import parser.ParserRunnable;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static volatile boolean stop = false;
    public static Set<String> uniqueWords = new HashSet<>();

    public static void main(String[] args) {
        for(String resource:args){
            Thread thread = new Thread(new ParserRunnable(resource));
            thread.start();
        }
    }
}
