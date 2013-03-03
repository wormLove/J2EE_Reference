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
        
        // Build a criteria with Projections.property() method
        {
            System.out.println("\n---Using critieria with Projection.property() method...");
            Criteria crit = session.createCriteria(Product.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.property("name"));
            projList.add(Projections.property("description"));
            crit.setProjection(projList);
            List results = crit.list();
            displayObjectsList(results);
            
        }
        
        // Build a criteria with Projection.avg() method
        {
            System.out.println("\n---Using critieria with Projection.avg() method...");
            Criteria crit = session.createCriteria(Product.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.max("price"));
            projList.add(Projections.min("price"));
            projList.add(Projections.avg("price"));
            projList.add(Projections.countDistinct("description"));
            crit.setProjection(projList);
            List results = crit.list();
            displayObjectsList(results);
        }
        
        // Build a criteria with Projections.rowCount() method
        {
            System.out.println("\n---Using critieria with Projection.avg() method...");
            Criteria crit = session.createCriteria(Product.class);
            crit.setProjection(Projections.rowCount());
            List results = crit.list();
            displayObjectList(results);
        }
        
        // Display tables
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");
        
    }
    
    // Below are utility methods
    
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
    
    static public void displayObjectList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext()) {
            Object obj = iter.next();
            
            System.out.println(obj);
        }
    }
    
    private static void prepareTestData(){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier Name 1");
        session.save(supplier1);
        
        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier Name 2");
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