/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */
import java.sql.*;

public class BatchUpdate {
    
    public static void main(String args[]) throws SQLException {
        
        ResultSet rs = null;
        String url = "jdbc:derby://localhost:1527/sample";
        
        Connection con;
        Statement stmt;
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        try {
            
            con = DriverManager.getConnection(url,
                    "app", "app");
            
            // Set this connection's auto-commit mode to false
            con.setAutoCommit(false);
            
            // Create a batch statement and add SQL commands to it
            stmt = con.createStatement();
            
            stmt.addBatch("INSERT INTO COFFEES " +
                    "VALUES('Amaretto', 49, 9.99, 0, 0)");
            stmt.addBatch("INSERT INTO COFFEES " +
                    "VALUES('Hazelnut', 49, 9.99, 0, 0)");
            stmt.addBatch("INSERT INTO COFFEES " +
                    "VALUES('Amaretto_decaf', 49, 10.99, 0, 0)");
            stmt.addBatch("INSERT INTO COFFEES " +
                    "VALUES('Hazelnut_decaf', 49, 10.99, 0, 0)");
            
            // Submits a batch of commands to the database for execution
            int [] updateCounts = stmt.executeBatch();
            
            // Makes all changes made since the previous commit/rollback permanent 
            // and releases any database locks currently held by this Connection object. 
            // This method should be used only when auto-commit mode has been disabled.
            con.commit();
            
            // Sets this connection's auto-commit mode to true. If a connection 
            // is in auto-commit mode, then all its SQL statements will be executed and 
            // committed as individual transactions. Otherwise, its SQL statements are 
            // grouped into transactions that are terminated by a call to either the method 
            // commit or the method rollback. By default, new connections are in auto-commit mode.
            con.setAutoCommit(true);
            
            // Display the result
            ResultSet uprs = stmt.executeQuery("SELECT * FROM COFFEES");
            
            System.out.println("Table COFFEES after insertion:");
            while (uprs.next()) {
                String name = uprs.getString("COF_NAME");
                int id = uprs.getInt("SUP_ID");
                float price = uprs.getFloat("PRICE");
                int sales = uprs.getInt("SALES");
                int total = uprs.getInt("TOTAL");
                System.out.print(name + "   " + id + "   " + price);
                System.out.println("   " + sales + "   " + total);
            }
            
            uprs.close();
            stmt.close();
            con.close();
            
        } catch(BatchUpdateException b) {
            System.err.println("-----BatchUpdateException-----");
            System.err.println("SQLState:  " + b.getSQLState());
            System.err.println("Message:  " + b.getMessage());
            System.err.println("Vendor:  " + b.getErrorCode());
            System.err.print("Update counts:  ");
            int [] updateCounts = b.getUpdateCounts();
            for (int i = 0; i < updateCounts.length; i++) {
                System.err.print(updateCounts[i] + "   ");
            }
            System.err.println("");
            
        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
            System.err.println("SQLState:  " + ex.getSQLState());
            System.err.println("Message:  " + ex.getMessage());
            System.err.println("Vendor:  " + ex.getErrorCode());
        }
    }
}

