package spamfilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filter {
    
    public String isSpam(String path, Counter counter) {
        double hamBuffer = 0.1;
        double spamBuffer = 0.1;
        double spamicity = getSpamicity(path, counter);
        if(spamicity > 1 + spamBuffer)
            return "is spam";
        else if(spamicity < 1 - hamBuffer)
            return "isn't spam";
        else
            return "don't know...";
    } 
    
    private double getSpamicity(String path, Counter counter) {
        double logOdds = 1.0; // given probability for spam is 0.5
        Probability hamProb, spamProb;
        Probability dummyProb = new Probability("dummyProbability", 0.0000001);
        for(String word : readMessageFile(path)){
            hamProb = counter.getProbability(word, Counter.MessageClass.ham);
            spamProb = counter.getProbability(word, Counter.MessageClass.spam);
            if(hamProb == null)
                hamProb = dummyProb;
            if(spamProb == null)
                spamProb = dummyProb;           
            logOdds += Math.log(spamProb.getValue() / hamProb.getValue());
        }
        return Math.exp(logOdds);
    }
    
    private List<String> readMessageFile(String path) {
        List<String> words = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(path).getFile());
            Scanner scanner = new Scanner(file);
            String line;
            
            while(scanner.hasNextLine()) {                
                line = scanner.nextLine().trim();
                for(String word : line.split(" ")){
                    words.add(word);
                }
            }
            scanner.close();
        } catch (Exception exc) {
            System.out.println("Virhe tiedostoa " + path + " luettaessa!");
            System.out.println(exc.getMessage());
        }
        return words;
    }
}
