package spamfilter;

public class App {
    
    public static void main(String[] args) {        
        Counter counter = new Counter();
        Filter filter = new Filter();
        String result = filter.isSpam("files/hamesim.txt", counter);        
        System.out.println("Hamesim.txt is spam? : " + result);
        result = filter.isSpam("files/spamesim.txt", counter);
        System.out.println("Spamesim.txt is spam? : " + result);
    }
    
}
