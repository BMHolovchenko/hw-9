package com.goit.hw9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsCounter {

    private static final String PATH = "D:\\GoIT\\HW\\src\\main\\resources\\words.txt";

    public static void freqCounter (File file) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
            String [] words = builder.toString().split("\\s+");
            counter(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void counter (String [] text) {
        List<String> wordList = Arrays.asList(text);
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String uniqueWord : wordList) {
            wordsMap.put(uniqueWord, Collections.frequency(wordList, uniqueWord));
        }
        wordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    private static void existingCheck (File file){
        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        File words = new File(PATH);
        existingCheck(words);
        freqCounter(words);
    }
}
