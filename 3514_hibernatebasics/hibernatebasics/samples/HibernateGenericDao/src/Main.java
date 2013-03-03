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
        EventDao eventDao = new EventDao();
        
        Event event = new Event();
        event.setName("JavaOne Conference");
        eventDao.create(event);
        
        event = new Event();
        event.setName("Whatever conference");
        eventDao.create(event);
        
        // Display tables
        HibernateUtil.checkData("select uid, name from events");
    }
    
}