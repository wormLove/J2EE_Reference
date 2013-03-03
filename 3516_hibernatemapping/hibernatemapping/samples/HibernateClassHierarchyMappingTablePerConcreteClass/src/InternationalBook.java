import java.util.*;

public class InternationalBook extends Book {
    
    private String languages;
    private int region;
    
    public InternationalBook() {
    }
    
    public InternationalBook(String title, String artist, Date purchaseDate, double cost, String language, int region) {
        super(title, artist, purchaseDate, cost);
        
        languages = language;
        this.region = region;
    }
    
    public void setLanguages(String s) {
        languages = s;
    }
    
    public String getLanguages() {
        return languages;
    }
    
    public void setRegion(int i) {
        region = i;
    }
    
    public int getRegion() {
        return region;
    }
}
