package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class textAnalyzer {

    public textAnalyzer(String path) {
        
    }

    //import text file as string
    public static String importFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String line = "";

        while((line = br.readLine())!=null) {
            sb.append(line);
            sb.append(" ");
        }

        br.close();
        fr.close();

        return sb.toString();
    }

    //replace dashes with spaces
    //replace double spaces with single spaces
    //replace punctuation with empty string
    //return string array with all words in same order as text
    public static String[] cleanText(String text) {
        text = text.replace("-", " ");
        text = text.replace("  ", " ");
        String[] cleanedText = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim().split(" ");
        return cleanedText;
    }

    //map words from cleaned text wordMap<word, <next word, number of next words>>
    public static Map<String, HashMap<String, Float>> mapWords(String[] text, Map<String, HashMap<String, Float>> wordMap) {
        for(int i = 0; i < text.length-1; i++) {
            HashMap<String, Float> tempNextWordMap = new HashMap<String, Float>();
            if(!wordMap.containsKey(text[i])){
                tempNextWordMap.put(text[i+1], 1f);
                wordMap.put(text[i], tempNextWordMap);
            } else {
                wordMap.get(text[i]).computeIfAbsent(text[i+1], v -> 0f);
                wordMap.get(text[i]).computeIfPresent(text[i+1], (k, v) -> v += 1f);
            }
        }
        return wordMap;
    }

    //function to take in wordMap(word, <nextword, number>) and output avgMap(word, <nextwword, avg>)
    public static Map<String,HashMap<String,Float>> analyzeText(Map<String, HashMap<String, Float>> wordMap) {

        Map<String, HashMap<String, Float>> avgMap = new HashMap<String, HashMap<String, Float>>();
        for (String word : wordMap.keySet()){
            HashMap<String, Float> nextWordsMap = wordMap.get(word);
            HashMap<String, Float> tempMap = new HashMap<String, Float>();
            Float nextWordCount = 0f;

            //get the sum of nextwords
            for(Float i : nextWordsMap.values()) {
                nextWordCount += i;
            }

            //put the nextWord and corresponding avg value into tempMap
            for(String nextWord: nextWordsMap.keySet()) {
                float avg = nextWordsMap.get(nextWord)/nextWordCount;
                tempMap.put(nextWord, avg);
            }

            //put the completed key and tempMap pair into avgMap
            avgMap.put(word, tempMap);
        }
        return avgMap;
    }
}
