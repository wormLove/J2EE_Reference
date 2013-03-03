import java.util.*;

import org.hibernate.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        // Set up database tables
        HibernateUtil.droptable("drop table Book");
        HibernateUtil.droptable("drop table SpecialEditionBook");
        HibernateUtil.droptable("drop table InternationalBook");
        HibernateUtil.setup("create table Book(id int,title varchar(20),artist varchar(20),purchasedate date,cost double)");
        HibernateUtil.setup("create table SpecialEditionBook(id int,title varchar(20),artist varchar(20),purchasedate date,cost double,newfeatures varchar(20))");
        HibernateUtil.setup("create table InternationalBook(id int,title varchar(20),artist varchar(20),purchasedate date,cost double,languages varchar(20),region int)");
            
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Book cd = new Book("Book", "R", new Date(), 9.99);
        SpecialEditionBook secd = new SpecialEditionBook("sBook", "R",
                new Date(), 9.99, "W");
        InternationalBook icd = new InternationalBook("IBook", "R", new Date(),
                9.99, "S", 4);
        
        session.save(cd);
        session.save(secd);
        session.save(icd);
        
        icd = new InternationalBook("IBook", "R", new Date(), 100.9, "T", 3);
        session.save(icd);
        
        tx.commit();
        
        session.close();
        
        // Display tables
        HibernateUtil.checkData("select * from Book");
        HibernateUtil.checkData("select * from SpecialEditionBook");
        HibernateUtil.checkData("select * from InternationalBook");
    }
}
