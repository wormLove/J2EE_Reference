import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up database tables
        HibernateUtil.droptable("drop table person");
        HibernateUtil.setup("create table person ( id int, cname VARCHAR(20))");
        
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
            session.save(p1);
            
            // Create another Person object and save it.
            Person p2 = new Person();
            p2.setName("Young Shin");
            session.save(p2);
            
            // Retrieve the person objects
            Person person = (Person)session. get(Person.class, p1.getId());
            System.out.println("First person retrieved = " + person.getName());
            person = (Person)session.get(Person.class, p2.getId());
            System.out.println("Second person retrieved = " + person.getName());
            
            tx.commit();
            tx = null;
        } catch ( HibernateException e ) {
            if ( tx != null ) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        // Display tables
        HibernateUtil.checkData("select * from person");
              
    }
       
}