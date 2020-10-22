package com.homework;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) {

        List<String> linesOfText = readTextFileInList(args[0]);

        String[] words = splitTextInWords(linesOfText);

        HashMap<String, Integer> hashMapOfWordAndCountPairs = getHashMapOfWordCount(words);
        Map<String, Integer> sortedMapOfWordAndCountPairs = sortMapByValue(hashMapOfWordAndCountPairs);

        sortedMapOfWordAndCountPairs.forEach((key, value) -> System.out.print(key + ":" + value + ", "));
    }

    private static HashMap<String, Integer> sortMapByValue(HashMap<String, Integer> hashMap){
        List<Entry<String, Integer>> list = new LinkedList<>(hashMap.entrySet());
        list.sort((o1, o2) -> (o2.getValue().compareTo(o1.getValue())));

        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<>();

        for(Entry<String, Integer> mapEntry : list){
            sortedHashMap.put(mapEntry.getKey(),mapEntry.getValue());
        }

        return sortedHashMap;
    }

    private static HashMap<String, Integer> getHashMapOfWordCount(String[] words) {
        HashMap<String, Integer> wordAndCountPairs = new HashMap<>();

        for(String word: words){
            if(!word.isBlank()) {
                String wordLowerCase = word.toLowerCase();
                if(wordAndCountPairs.containsKey(wordLowerCase)){
                    wordAndCountPairs.put(wordLowerCase, wordAndCountPairs.get(wordLowerCase) + 1);
                } else {
                    wordAndCountPairs.put(wordLowerCase, 1);
                }
            }
        }
        return wordAndCountPairs;
    }

    private static String[] splitTextInWords(List<String> linesOfText) {
        String textToSplit = linesOfText.toString();
        return textToSplit.replaceFirst("^/", "").split("\\W+");
    }

    private static List<String> readTextFileInList(String fileName) {
        List<String> linesOfText = Collections.emptyList();

        try{
            linesOfText = Files.readAllLines(Paths.get(fileName), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesOfText;
    }
}
