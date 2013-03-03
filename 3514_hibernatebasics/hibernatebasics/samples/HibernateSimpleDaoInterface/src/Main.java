
import java.util.*;


public class Main {
    
    public static void main(String[] args) {
        
        // Set up database table
        HibernateUtil.droptable("drop table EVENTS");
        HibernateUtil.setup("create table EVENTS ( uid int, name VARCHAR(20), start_Date date, duration int)");
        
        // Create Dao object and assign it to an interface and use
        // an SimpleEventDao implementation class.
        SimpleEventDaoInterface eventDao = new SimpleEventDao();
        
        // Perform create Dao operation
        System.out.println("Performing create Dao operation....");
        Event event1 = new Event();
        event1.setName("JavaOne conference");
        eventDao.create(event1);
        
        Event event2 = new Event();
        event2.setName("Noname conference");
        eventDao.create(event2);
        
        // Perform find Dao operation
        System.out.println("Perrforming find Dao operation...");
        Event foundEvent = eventDao.find(event1.getId());
        System.out.println("Name of the event found = " + foundEvent.getName());
        
        // Perform update Dao operation
        System.out.println("Perrforming update Dao operation...");
        event1.setName("New Conference");
        eventDao.update(event1);
        
        // Perform findAll Dao operation
        System.out.println("Performing findAll Dao operation...");
        List eventList = eventDao.findAll();
        System.out.println("Number of entries in the database table = " + eventList.size());
        
        // Create Dao object and assign it to an interface and use
        // an SimpleEventDaoAnotherImpl implementation class.
        eventDao = new SimpleEventDaoAnotherImpl();
        
        // Perform find Dao operation
        System.out.println("Perrforming find Dao operation...");
        foundEvent = eventDao.find(event1.getId());
        System.out.println("Name of the event found = " + foundEvent.getName());
        
        // Display the contents of the database table
        HibernateUtil.checkData("select uid, name from events");
        
    }
    
}