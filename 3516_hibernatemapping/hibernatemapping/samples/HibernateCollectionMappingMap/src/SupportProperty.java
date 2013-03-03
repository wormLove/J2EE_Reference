import java.util.*;

public class SupportProperty {
    
    private int id;
    private String name;
    private Map properties;
    
    public SupportProperty() {
    }
    
    public void setId(int i) {
        id = i;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String s) {
        name = s;
    }
    
    public String getName() {
        return name;
    }
    
    public void setProperties(Map m) {
        properties = m;
    }
    
    public Map getProperties() {
        return properties;
    }
}
