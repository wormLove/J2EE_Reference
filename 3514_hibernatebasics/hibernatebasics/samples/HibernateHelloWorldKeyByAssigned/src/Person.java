import java.io.Serializable;

public class Person implements Serializable {
    
    private int id;
    private String name;
    
    protected Person() {
    }
    
    public Person(int id, String message) {
        this.id = id;
        this.name = message;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
        
}