package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    //use recursion to obtain list of text files
    public static List<File> checkForFiles(File file, List<File> fileList) {
        if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                checkForFiles(innerFile, fileList);
            }
        } else if(file.isFile()) {
            fileList.add(file);
        }

        return fileList;
    }

    public static void main(String[] args) throws IOException {

        Map<String, HashMap<String, Float>> wordMap = new HashMap<String, HashMap<String, Float>>();

        File root = new File(args[0]);
        List<File> fileList = new ArrayList<File>();
        fileList = checkForFiles(root, fileList);

        for (File file : fileList) {
            String text = textAnalyzer.importFile(file);
            String[] cleanedText = textAnalyzer.cleanText(text);
            wordMap = textAnalyzer.mapWords(cleanedText, wordMap);
        }

        // start loop
        // import file
        // String text = textAnalyzer.importFile(innerFile);
        // clean file
        // String[] cleanedText = textAnalyzer.cleanText(text);
        // map text
        // wordMap = textAnalyzer.mapWords(cleanedText, wordMap);
        // loop

        // analyze text
        Map<String, HashMap<String, Float>> avgMap = new HashMap<String, HashMap<String, Float>>();
        avgMap = textAnalyzer.analyzeText(wordMap);

        for (String word : avgMap.keySet()) {
            System.out.println(word);
            for (String nextWord : avgMap.get(word).keySet()) {
                System.out.printf("    %s %.1f\n", nextWord, avgMap.get(word).get(nextWord));
            }
        }
    }
}
