
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;
import org.hibernate.event.*;
import org.hibernate.event.def.*;


public class Main {
   
    public static void main(String[] args) throws Exception {
        
        // Set up database tables
        HibernateUtil.droptable("drop table m_events");
        HibernateUtil.droptable("drop table m_speakers");
        HibernateUtil.droptable("drop table event_speakers");
        HibernateUtil.setup("create table m_events ( uid int, name VARCHAR(20), start_Date date, duration int, location_id int)");
        HibernateUtil.setup("create table m_speakers ( uid int, firstname VARCHAR(20), lastName VARCHAR(20))");
        HibernateUtil.setup("create table event_speakers (elt int, event_id int, speaker_id int)");
        
        // Create Session and Transaction objects
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        // Create the first event with multiple speakers
        EventManyToMany event = new EventManyToMany();
        event.setName("JavaOne conference");
        event.setSpeakers(new HashSet());
        event.getSpeakers().add(new SpeakerManyToMany("John", "Smith", event));
        event.getSpeakers().add(new SpeakerManyToMany("Joe", "Smith", event));
        event.getSpeakers().add(new SpeakerManyToMany("Sang", "Shin", event));
        
        // Save event object
        session.save(event);
        
        // Create the second event with multiple speakers
        EventManyToMany event2 = new EventManyToMany();
        event2.setName("Passion Conference");
        event2.setSpeakers(new HashSet());
        event2.getSpeakers().add(new SpeakerManyToMany("Sang", "Shin", event2));
        event2.getSpeakers().add(new SpeakerManyToMany("Shelly", "Lumm", event2));
        event2.getSpeakers().add(new SpeakerManyToMany("Diane", "Woon", event2));
        
        // Save event object
        session.save(event2);
        
        // Load an Event object and see Speaker objects get also read
        System.out.println("Event name = " + event.getName());
        event = (EventManyToMany) session.load(EventManyToMany.class, event.getId());
        Set speakers = event.getSpeakers();
        
        for (Iterator i = speakers.iterator(); i.hasNext();) {
            SpeakerManyToMany speaker = (SpeakerManyToMany) i.next();
            System.out.println("    Speaker's first name = " + speaker.getFirstName());
            System.out.println("    Speaker's last name = " + speaker.getLastName());
            System.out.println("    Speaker id = " + speaker.getId());
        }
        
        System.out.println("Event name = " + event2.getName());
        event2 = (EventManyToMany) session.load(EventManyToMany.class, event2.getId());
        Set speakers2 = event2.getSpeakers();
        
        for (Iterator i = speakers2.iterator(); i.hasNext();) {
            SpeakerManyToMany speaker2 = (SpeakerManyToMany) i.next();
            System.out.println("    Speaker's first name = " + speaker2.getFirstName());
            System.out.println("    Speaker's last name = " + speaker2.getLastName());
            System.out.println("    Speaker id = " + speaker2.getId());
        }
        
        tx.commit();
        HibernateUtil.closeSession();
        
        HibernateUtil.sessionFactory.close();
        
        // Display tables
        HibernateUtil.checkData("select * from m_events");
        HibernateUtil.checkData("select * from m_speakers");
        HibernateUtil.checkData("select * from event_speakers");
    }
    
}