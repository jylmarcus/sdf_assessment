package src;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException{
        
        Map<String, HashMap<String,Float>> wordMap = new HashMap<String, HashMap<String, Float>>();

        File root = new File(args[0]);
        
        for(File file : root.listFiles()){
            if(file.isDirectory()){
                for(File innerFile : file.listFiles()){
                    String text = textAnalyzer.importFile(innerFile);
                    String[] cleanedText = textAnalyzer.cleanText(text);
                    wordMap = textAnalyzer.mapWords(cleanedText, wordMap);
                }
            }
        }

        //start loop
        //import file
        //String text = textAnalyzer.importFile(innerFile);
        //clean file
        //String[] cleanedText = textAnalyzer.cleanText(text);
        //map text
        //wordMap = textAnalyzer.mapWords(cleanedText, wordMap);
        //loop

        //analyze text
        Map<String, HashMap<String, Float>> avgMap = new HashMap<String, HashMap<String, Float>>();
        avgMap = textAnalyzer.analyzeText(wordMap);

        for (String word : avgMap.keySet()) {
            System.out.println(word);
            for(String nextWord: avgMap.get(word).keySet()){
                System.out.printf("    %s %.1f\n", nextWord, avgMap.get(word).get(nextWord));
            }
          } 
    }
}
