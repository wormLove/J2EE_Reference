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
        
        
        // Build a simple criteria with no restriction
        {
            System.out.println("\n---Using criteria with no restriction...");
            Criteria crit = session.createCriteria(Product.class);
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a simple criteria with paging
        {
            System.out.println("\n---Using criteria with paging...");
            Criteria crit = session.createCriteria(Product.class);
            crit.setFirstResult(1);   // Starting row
            crit.setMaxResults(2);    // Size of each page
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a criteria with two conditions
        {
            System.out.println("\n---Using criteria with two Restrictions...");
            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.gt("price",new Double(25.0)));
            crit.add(Restrictions.like("name","P%"));
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a criteria with a Restriction.like
        {
            System.out.println("\n---Using criteria with Restriction.like...");
            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.like("name","Product 1%"));
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a criteria with Restrictions.or
        {
            System.out.println("\n---Using criteria with Restrictions.or... ");
            Criteria crit = session.createCriteria(Product.class);
            Criterion price = Restrictions.gt("price",new Double(25.0));
            Criterion name = Restrictions.like("name","Mou%");
            LogicalExpression orExp = Restrictions.or(price,name);
            crit.add(orExp);
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a criteria with Criteria.DISTINCT_ROOT_ENTITY
        {
            System.out.println("\n---Using criteria with Criteria.DISTINCT_ROOT_ENTITY... ");
            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.gt("price",new Double(1.0)));
            crit.add(Restrictions.like("name","P%"));
            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            
            List results = crit.list();
            displayProductsList(results);
        }
        
        // Build a criteria with Order
        {
            System.out.println("\n---Using criteria with Order... ");
            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.gt("price",new Double(1.0)));
            crit.addOrder(Order.desc("price"));
            
            List results = crit.list();
            displayProductsList(results);
            
        }
        
        // Build a criteria with Association and Order
        {
            System.out.println("\n---Using criteria with Association and Order... ");
            Criteria crit = session.createCriteria(Supplier.class);
            crit.addOrder(Order.desc("name"));
            Criteria prdCrit = crit.createCriteria("products");
            prdCrit.add(Restrictions.gt("price",new Double(25.0)));
            
            List results = prdCrit.list();
            displaySupplierList(results);
            
        }
        
        // Build a criteria with Unique Result
        {
            try{
                System.out.println("\n---Using criteria with Unique Result... ");
                Criteria crit = session.createCriteria(Product.class);
                Criterion price = Restrictions.gt("price",new Double(25.0));
                Product product = (Product) crit.uniqueResult();
                
                List results = new ArrayList();
                results.add(product);
                displayProductsList(results);
            } catch(org.hibernate.NonUniqueResultException e){
                System.out.println("org.hibernate.NonUniqueResultException received");
            }
        }
        
        // Display tables
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");
        
    }
    
    // Below are utility methods
    
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