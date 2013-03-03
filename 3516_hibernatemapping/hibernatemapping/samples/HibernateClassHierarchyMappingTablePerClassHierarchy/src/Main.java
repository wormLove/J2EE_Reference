import java.util.*;

import org.hibernate.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        // Set up database tables
        HibernateUtil.droptable("drop table Book");
        HibernateUtil.setup("create table Book(id int,title varchar(20),artist varchar(20),purchasedate date,cost double,newfeatures varchar(20),languages varchar(20),region int,Book_type varchar(20))");
        
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Book cd = new Book("Book", "R", new Date(), 9.99);
        SpecialEditionBook secd = new SpecialEditionBook("sBook", "R", new Date(), 9.99, "W");
        InternationalBook icd = new InternationalBook("IBook", "R", new Date(), 9.99, "S", 4);
        
        session.save(cd);
        session.save(secd);
        session.save(icd);
        
        tx.commit();
        
        session.close();
        
        // Display tables
        HibernateUtil.checkData("select * from Book");
    }
}
