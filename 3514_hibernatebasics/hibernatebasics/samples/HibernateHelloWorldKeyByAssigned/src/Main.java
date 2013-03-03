import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up database tables
        HibernateUtil.droptable("drop table Person");
        HibernateUtil.setup("create table Person ( id int, cname VARCHAR(20))");
        
        // Create SessionFactory and Session object
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        Session session = sessions.openSession();
        
        // Perform life-cycle operations under a transaction
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            // Create a Person object and save it
            Person p1 = new Person();
            p1.setName("Sang Shin");
            
            // Since the Id generation scheme is now set as "assigned",
            // you have to assign it.
            p1.setId(233);
            session.save(p1);
            
            // Create another Person object and save it.
            Person p2 = new Person();
            p2.setName("Young Shin");
            
            // Since the Id generation scheme is now set as "assigned",
            // you have to assign it.
            p2.setId(411);
            session.save(p2);
            
            // Retrieve the person objects
            Person person = (Person)session. get(Person.class, p1.getId());
            System.out.println("First person, Id generated via assigned = " + person.getId());
            person = (Person)session.get(Person.class, p2.getId());
            System.out.println("Second person, Id generated via assigned = " + person.getId());
            
            tx.commit();
            tx = null;
        } catch ( HibernateException e ) {
            if ( tx != null ) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        // Display tables
        HibernateUtil.checkData("select * from Person");
        
    }
    
}