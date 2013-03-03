import java.io.Serializable;

public class Student implements Serializable {
    
    private int id;
    private String school;
    private String grade;
    
    protected Student() {
    }
    
    public Student(int id, String school) {
        this.id = id;
        this.school = school;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getSchool() {
        return school;
    }
    
    public void setSchool(String name) {
        this.school = name;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}