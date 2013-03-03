import java.io.Serializable;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;
import org.hibernate.event.*;
import org.hibernate.event.def.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        // Set up database tables
        HibernateUtil.droptable("drop table supportproperty");
        HibernateUtil.droptable("drop table properties");
        HibernateUtil.setup("create table supportproperty (id int,name varchar(20))");
        HibernateUtil.setup("create table properties (id int,property_name VARCHAR(20),property_value varchar(20))");
        
        // Create Session and Transaction object
        Session session = HibernateUtil.currentSession();
        Transaction transaction = null;
        
        Map p2 = null;
        try {
            transaction = session.beginTransaction();
            
            // Create Domain object, SupportProperty object has a Map
            // object.
            SupportProperty sp = new SupportProperty();
            sp.setName("MyProperties");
            
            HashMap h = new HashMap();
            h.put("car", "ford");
            h.put("house", "lexington");
            sp.setProperties(h);
            
            session.save(sp);
            
            // Create another object instance
            SupportProperty sp2 = new SupportProperty();
            sp2.setName("YourProperties");
            HashMap h2 = new HashMap();
            h2.put("tv", "samsung");
            h2.put("house", "lexington");
            sp2.setProperties(h2);
            
            session.save(sp2);
            
            SupportProperty sp3 = (SupportProperty)session.load(SupportProperty.class, new Integer(sp.getId()));
            p2 = sp2.getProperties();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }  finally {
            session.close();
        }
        
        System.out.println("car property = " + p2.get("car"));
        System.out.println("tv property = " + p2.get("tv"));
        
        // Display tables
        HibernateUtil.checkData("select * from supportproperty");
        HibernateUtil.checkData("select * from properties");
    }
}
