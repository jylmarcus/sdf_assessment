package src;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException{
        
        Map<String, HashMap<String,Float>> wordMap = new HashMap<String, HashMap<String, Float>>();

        //start loop
        //import file
        String text = textAnalyzer.importFile(new File(args[0]));
        //clean file
        String[] cleanedText = textAnalyzer.cleanText(text);
        //map text
        wordMap = textAnalyzer.mapWords(cleanedText, wordMap);
        //loop

        //analyze text
        Map<String, HashMap<String, Float>> avgMap = new HashMap<String, HashMap<String, Float>>();
        avgMap = textAnalyzer.analyzeText(wordMap);

        for (String word : avgMap.keySet()) {
            System.out.println(word);
            for(String nextWord: avgMap.get(word).keySet()){
                System.out.println("    " + nextWord + " " + avgMap.get(word).get(nextWord));
            }
          }
    }
}
