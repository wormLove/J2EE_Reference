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
        HibernateUtil.droptable("drop table Book");
        HibernateUtil.droptable("drop table SpecialEditionBook");
        HibernateUtil.droptable("drop table InternationalBook");
        HibernateUtil.setup("create table Book(id int,title varchar(20),artist varchar(20),purchasedate date,cost double)");
        HibernateUtil.setup("create table SpecialEditionBook(id int,newfeatures varchar(40))");
        HibernateUtil.setup("create table InternationalBook(id int,languages varchar(40),region int)");
        
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        // Create objects which have inheritance relationship
        Book cd = new Book("Book", "R", new Date(), 9.99);
        SpecialEditionBook secd = new SpecialEditionBook("sBook", "R",
                new Date(), 9.99, "W");
        InternationalBook icd = new InternationalBook("IBook", "R", new Date(),
                9.99, "S", 4);
        
        session.save(cd);
        session.save(secd);
        session.save(icd);
        tx.commit();
        
        session.flush();
        session.close();
        
        // Display tables
        HibernateUtil.checkData("select * from Book");
        HibernateUtil.checkData("select * from SpecialEditionBook");
        HibernateUtil.checkData("select * from InternationalBook");
    }
}
