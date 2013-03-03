import java.io.Serializable;

public class Person implements Serializable {
    
    private String id;
    private String name;
    
    protected Person() {
    }
    
    public Person(String id, String message) {
        this.id = id;
        this.name = message;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
        
}