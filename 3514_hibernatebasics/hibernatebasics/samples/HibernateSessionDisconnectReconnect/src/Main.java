import java.util.*;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;

public class Main {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        // Set up database table and test data
        HibernateUtil.droptable("drop table Supplier");
        HibernateUtil.droptable("drop table Product");
        HibernateUtil.setup("create table Supplier ( id int, name VARCHAR(20))");
        HibernateUtil.setup("create table Product ( id int, name VARCHAR(20), description VARCHAR(20), price double,supplierId int)");
        prepareTestData();
        
        // Create Session object
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        
        // Perform a query operation
        Query query = session.createQuery("from Product order by name");
        List list = query.list();
        Iterator i = list.iterator();
        while(i.hasNext()) {
            Product p = (Product)i.next();
            System.out.println("Product name = " + p.getName());
        }
        
        // Disconnect the Session from the current JDBC connection.
        // If the connection was obtained by Hibernate close it and return
        // it to the connection pool; otherwise, return it to the application.
        Connection c = session.disconnect();
        
        // Reconnect to the given JDBC connection. This is used by
        // applications which require long transactions and use
        // application-supplied connections.
        session.reconnect(c);
        
        // Create a Query object
        query = session.createQuery("from Product order by name");
        list = query.list();
        i = list.iterator();
        while(i.hasNext()) {
            Product p = (Product)i.next();
            System.out.println("Product name = " + p.getName());
        }
        
        // Display tables
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");
        
    }
    
    // Below are utility methods
    
    static public void displaySupplierList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No suppliers to display.");
            return;
        }
        while (iter.hasNext()) {
            Supplier supplier = (Supplier) iter.next();
            String msg = supplier.getName();
            System.out.println(msg);
        }
    }
    
    private static void prepareTestData(){
        Session session = HibernateUtil.currentSession();
        
        Transaction tx = session.beginTransaction();
        Supplier supplier1 = new Supplier();
        supplier1.setName("Sun Microsystems");
        session.save(supplier1);
        
        Supplier supplier2 = new Supplier();
        supplier2.setName("SamSung");
        session.save(supplier2);
        
        Product product1 = new Product("Sun workstation","Cool machine", 2.0);
        product1.setSupplier(supplier1);
        supplier1.getProducts().add(product1);
        session.save(product1);
        
        Product product12 = new Product("Solaris OS","Coolest OS", 22.0);
        product12.setSupplier(supplier1);
        supplier1.getProducts().add(product12);
        session.save(product12);
        
        Product product2 = new Product("SamSung Phone", "Cool phone", 30.0);
        product2.setSupplier(supplier2);
        supplier2.getProducts().add(product2);
        session.save(product2);
        
        tx.commit();
        HibernateUtil.closeSession();
    }
}