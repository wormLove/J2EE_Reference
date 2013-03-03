
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

        // Display tables
        HibernateUtil.checkData("select * from Supplier");
        HibernateUtil.checkData("select * from Product");

        Session session = HibernateUtil.currentSession();

        // Build a criteria with Association with FetchMode.JOIN
        {
            System.out.println("\n---Using criteria with Association with FetchMode.JOIN... ");
            Criteria crit = session.createCriteria(Supplier.class);
            crit.setFetchMode("products", FetchMode.JOIN);
            crit.setFirstResult(1);
            crit.setMaxResults(2);
            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List results = crit.list();
            displaySupplierList(results);

        }


    }

    // Below are utility methods
    public static void displayProductsList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No products to display.");
            return;
        }
        while (iter.hasNext()) {
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

    private static void prepareTestData() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier Name 1");
        session.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier Name 2");
        session.save(supplier2);

        Supplier supplier3 = new Supplier();
        supplier3.setName("Supplier Name 3");
        session.save(supplier3);

        Supplier supplier4 = new Supplier();
        supplier4.setName("Supplier Name 4");
        session.save(supplier4);

        Product product1 = new Product("Product 1", "Name for Product 1", 20.0);
        product1.setSupplier(supplier1);
        supplier1.getProducts().add(product1);
        session.save(product1);

        Product product2 = new Product("Product 2", "Name for Product 2", 30.0);
        product2.setSupplier(supplier1);
        supplier1.getProducts().add(product2);
        session.save(product2);

        Product product3 = new Product("Product 3", "Name for Product 3", 22.0);
        product3.setSupplier(supplier2);
        supplier2.getProducts().add(product3);
        session.save(product3);

        Product product4 = new Product("Product 4", "Name for Product 4", 70.0);
        product4.setSupplier(supplier3);
        supplier3.getProducts().add(product4);
        session.save(product4);

        tx.commit();
        HibernateUtil.closeSession();
    }
}
