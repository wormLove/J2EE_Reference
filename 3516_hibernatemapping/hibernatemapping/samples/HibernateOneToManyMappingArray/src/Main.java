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
        
        Session session = HibernateUtil.currentSession();
        
        // Group has an array of Story objects
        Group sp = new Group("Singers");
        sp.setStories(new Story[]{new Story("Tom Jones"), new Story("Beatles")});
        Group sp2 = new Group("Politicians");
        sp2.setStories(new Story[]{new Story("Bill Clinton"), new Story("Ronald Reagan")});
        
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
