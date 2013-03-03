import java.util.*;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.criterion.*;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up database tables
        HibernateUtil.droptable("drop table EVENTS");
        HibernateUtil.setup("create table EVENTS ( uid int, name VARCHAR(20), start_Date date, duration int)");
        
        // Perform persistence operations through Dao object
        EventDaoInterface eventDao = new EventDao();
        
        Event event = new Event();
        event.setName("JavaOne Conference");
        eventDao.create(event);
        
        event = new Event();
        event.setName("Whatever conference");
        eventDao.create(event);
        
        // Perform persistence operations through Dao object
        EventDaoInterface eventDao2 = new EventDaoAnotherImpl();
        
        Event event2 = new Event();
        event2.setName("Event1000");
        eventDao2.create(event2);
        
        event2 = new Event();
        event2.setName("Event2000");
        eventDao2.create(event2);
        
        // Display tables
        HibernateUtil.checkData("select uid, name from events");
    }
    
}