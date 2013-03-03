import java.util.*;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up database tables and test data
        HibernateUtil.droptable("drop table Product");
        HibernateUtil.setup("create table Product ( id int, name VARCHAR(30), description VARCHAR(30), price double,supplierId int)");
        prepareTestData();
        
        // Perform the read operation
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        
        // Perform a read operation just to verify the product name in the database
        Product product = null;
        product = (Product)session.createQuery("from Product where name='ProductNameOld1'").uniqueResult();
        System.out.println("Name of product retrieved from database = " + product.getName());
        
        // Begin transaction and do Rollback
        System.out.println("Begin transaction...");
        Transaction tx = session.beginTransaction();
        System.out.println("Changing name of product to ProductNameNew...");
        product.setName("ProductNameNew");
        
        System.out.println("\nAbout to do Rollback...");
        System.out.println("Name of product before rollback = " + product.getName());
        tx.rollback();
        System.out.println("After Rollback...");
        System.out.println("Name of product after rollback = " + product.getName());
        
        // Begin transaction and do Refresh and commit
        tx = session.beginTransaction();
        System.out.println("\nAbout to Refresh...");
        session.refresh(product);
        System.out.println("Name of product after refresh = " + product.getName());
        tx.commit();
        System.out.println("Name of product after commit = " + product.getName());
        
        // Display tables
        HibernateUtil.checkData("select * from Product");
        
    }
    
    // Below are utility methods
    
    private static void prepareTestData(){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Product product1 = new Product("ProductNameOld1","Description for Product 1", 2.0);
        session.save(product1);
        
        Product product12 = new Product("ProductNameOld2","Description for Product 2", 22.0);
        session.save(product12);
        
        Product product2 = new Product("ProductNameOld3", "Description for Product 3", 30.0);
        session.save(product2);
        
        tx.commit();
        HibernateUtil.closeSession();
    }
      
}