package spamfilter;

public class Probability {
    
    private String word;
    private double value;
    
    public Probability(String word, double value) {
        setWord(word);
        setValue(value);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
