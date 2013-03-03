import java.util.*;

import java.sql.*;
import org.hibernate.*;
import org.hibernate.criterion.*;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up database tables
        HibernateUtil.droptable("drop table Supplier");
        HibernateUtil.droptable("drop table Product");
        HibernateUtil.setup("create table Supplier ( id int, name VARCHAR(20))");
        HibernateUtil.setup("create table Product ( id int, name VARCHAR(20), description VARCHAR(40), price double,supplierId int)");
        
        prepareTestData();
        Session session = HibernateUtil.currentSession();
        
        // Build a criteria with Query By Example
        {
            System.out.println("\n---Using criteria with Query By Example...");
            Criteria crit = session.createCriteria(Supplier.class);
            Supplier supplier = new Supplier();
            supplier.setName("SamSung");
            crit.add(Example.create(supplier));
            List results = crit.list();
            
            displaySupplierList(results);
        }
        
        // Build a criteria with Query By Example 2
        {
            System.out.println("\n---Using criteria with Query By Example 2...");
            Criteria prdCrit = session.createCriteria(Product.class);
            Product product = new Product();
            product.setName("P%");
            Example prdExample = Example.create(product);
            prdExample.excludeProperty("price");
            prdExample.enableLike();
            Criteria suppCrit = prdCrit.createCriteria("supplier");
            Supplier supplier = new Supplier();
            supplier.setName("Supplier 1");
            suppCrit.add(Example.create(supplier));
            prdCrit.add(prdExample);
            List results = prdCrit.list();
            
            displayProductsList(results);
        }
        
        // Display tables
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");
        
    }
    
    // Below are the utility methods
    
    static public void displayObjectsList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext()) {
            System.out.println("New object");
            Object[] obj = (Object[]) iter.next();
            for (int i=0;i<obj.length;i++) {
                System.out.println(obj[i]);
            }
            
            
        }
    }
    
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
    
    public static void displayProductsList(List list){
        Iterator iter = list.iterator();
        if (!iter.hasNext()){
            System.out.println("No products to display.");
            return;
        }
        while (iter.hasNext()){
            Product product = (Product) iter.next();
            String msg = product.getSupplier().getName() + "\t";
            msg += product.getName() + "\t";
            msg += product.getPrice() + "\t";
            msg += product.getDescription();
            System.out.println(msg);
        }
    }
    
    private static void prepareTestData(){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Supplier supplier1 = new Supplier();
        supplier1.setName("SamSung");
        session.save(supplier1);
        
        Supplier supplier2 = new Supplier();
        supplier2.setName("Sony");
        session.save(supplier2);
        
        Product product1 = new Product("Product 1","Name for Product 1", 2.0);
        product1.setSupplier(supplier1);
        supplier1.getProducts().add(product1);
        session.save(product1);
        
        Product product12 = new Product("Product 2","Name for Product 2", 22.0);
        product12.setSupplier(supplier1);
        supplier1.getProducts().add(product12);
        session.save(product12);
        
        Product product2 = new Product("Product 3", "Name for Product 3", 30.0);
        product2.setSupplier(supplier2);
        supplier2.getProducts().add(product2);
        session.save(product2);
        
        tx.commit();
        HibernateUtil.closeSession();
    }
}