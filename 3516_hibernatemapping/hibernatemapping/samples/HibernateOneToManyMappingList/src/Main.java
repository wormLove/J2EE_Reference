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
        HibernateUtil.droptable("drop table grouptable");
        HibernateUtil.droptable("drop table story");
        HibernateUtil.setup("create table grouptable (id int,name varchar(20))");
        HibernateUtil.setup("create table story (id int,info varchar(40),idx int,parent_id int)");
        
        // Create Session object
        Session session = HibernateUtil.currentSession();
        
        // Create an Group object which has one to many relationship
        // with Story objects.       
        ArrayList list = new ArrayList();
        list.add(new Story("Tom Jones"));
        list.add(new Story("Beatles"));
        list.add(new Story("Elvis"));
        
        Group sp = new Group("Singers");    
        sp.setStories(list);
        
        ArrayList list2 = new ArrayList();
        list2.add(new Story("Bill Clinton"));
        list2.add(new Story("Ronald Reagan"));
        
        Group sp2 = new Group("Politicians");    
        sp2.setStories(list2);
        
        // Perform save operation in a transactional manner
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.save(sp);
            session.save(sp2);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }  finally {
            session.close();
        }
        
        // Display tables
        HibernateUtil.checkData("select * from grouptable");
        HibernateUtil.checkData("select * from story");
    }
}
