import java.io.*;
import java.util.*;

public class Book {
    int id;
    String title;
    String artist;
    Date	purchaseDate;
    double cost;
    
    public Book() {
    }
    
    public Book(String title, String artist, Date purchaseDate, double cost) {
        this.title = title;
        this.artist = artist;
        this.purchaseDate = purchaseDate;
        this.cost = cost;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public void setPurchasedate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public Date getPurchasedate() {
        return purchaseDate;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getCost() {
        return cost;
    }
}