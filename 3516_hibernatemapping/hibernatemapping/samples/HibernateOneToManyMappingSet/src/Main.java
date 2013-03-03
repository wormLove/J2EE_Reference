
import java.util.*;

import org.hibernate.*;
import org.hibernate.criterion.*;

public class Main {
    
    
    public static void main(String[] args) {
        
        // Set up database tables
        HibernateUtil.droptable("drop table EVENTS");
        HibernateUtil.droptable("drop table speakers");
        HibernateUtil.setup("create table EVENTS ( uid int, name VARCHAR(20), start_Date date, duration int, location_id int)");
        HibernateUtil.setup("create table speakers ( uid int, event_id int, firstname VARCHAR(20), lastName VARCHAR(20))");
        
        // Create a Session and Transaction objects
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        // Create an Event object which has one to many relationship
        // with Speaker objects.
        Event event = new Event();
        event.setName("Java Conference");
        event.setSpeakers(new HashSet());
        event.getSpeakers().add(new Speaker("Sang", "Shin"));
        event.getSpeakers().add(new Speaker("Dave", "Smith"));
        event.getSpeakers().add(new Speaker("Bill", "Clinton"));
        
        // Saving an Event object will also save Speaker objects that
        // belong to the Event object.
        session.saveOrUpdate(event);
        
        // Create an Event object which has one to many relationship
        // with Speaker objects.
        Event event2 = new Event();
        event2.setName("Passion Conference");
        event2.setSpeakers(new HashSet());
        event2.getSpeakers().add(new Speaker("James", "Gosling"));
        event2.getSpeakers().add(new Speaker("Daniel", "Jones"));
        
        // Saving an Event object will also save Speaker objects that
        // belong to the Event object.
        session.saveOrUpdate(event2);
        
        tx.commit();
        HibernateUtil.closeSession();
        HibernateUtil.sessionFactory.close();
        
        // Display tables
        HibernateUtil.checkData("select * from speakers");
        HibernateUtil.checkData("select * from events");
    }
    
}