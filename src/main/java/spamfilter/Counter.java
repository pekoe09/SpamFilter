package spamfilter;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Counter {
    
    private HashMap<String, Double> spamProbs, hamProbs;
    
    public Counter(){
        load();
    }
    
    private void load() {
        spamProbs = new HashMap<>();
        hamProbs = new HashMap<>();
        readFile(MessageClass.spam);
        readFile(MessageClass.ham);        
    }
    
    private void readFile(MessageClass msgClass) {
        HashMap<String, Double> probs;
        int totalCount;
        String dataFile = "";
        if(msgClass == MessageClass.ham) {
            probs = hamProbs;
            totalCount = 290673;
            dataFile = "files/hamcount.txt";
        }
        else {
            probs = spamProbs;
            totalCount = 75268;
            dataFile = "files/spamcount.txt";
        }
        
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(dataFile).getFile());
            Scanner scanner = new Scanner(file);
            String line;
            String[] lineParts;
            int count;
            String word;
            while(scanner.hasNextLine()) {                
                lineParts = scanner.nextLine().trim().split(" ");
                count = Integer.parseInt(lineParts[0]);
                word = lineParts[1];
                probs.put(word, (double)count/totalCount);
            }
            scanner.close();
        } catch (Exception exc) {
            System.out.println("Virhe tiedostoa " + dataFile + " luettaessa!");
            System.out.println(exc.getMessage());
        }
    }
    
    public Probability getProbability(String word, MessageClass msgClass) {
        HashMap<String, Double> probs = null;
        probs = msgClass == MessageClass.ham ? hamProbs : spamProbs;
        if(!probs.containsKey(word))
            return null;
        else
            return new Probability(word, probs.get(word));
    }
    
    public enum MessageClass {
        spam,
        ham
    }
    
}
