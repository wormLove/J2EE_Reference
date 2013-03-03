import java.util.*;

public class SpecialEditionBook extends Book {
    
    private String newfeatures;
    
    public SpecialEditionBook() {
    }
    
    public SpecialEditionBook(String title, String artist, Date purchaseDate, double cost, String features) {
        super(title, artist, purchaseDate, cost);
        
        newfeatures = features;
    }
    
    public void setNewfeatures(String s) {
        newfeatures = s;
    }
    
    public String getNewfeatures() {
        return newfeatures;
    }
}
