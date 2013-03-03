/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class TypeConcurrency {
    
    public static void main(String args[]) {
        
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
            
            // Set the concurrency mode for a ResultSet object as "may NOT be updated"
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            ResultSet srs = stmt.executeQuery("SELECT * FROM COFFEES");
            
            // Retrieve the type of this ResultSet object.
            // Could return one of the following 3
            //      -ResultSet.TYPE_FORWARD_ONLY
            //      -ResultSet.TYPE_SCROLL_INSENSITIVE
            //      -ResultSet.TYPE_SCROLL_SENSITIVE 
            int type = srs.getType();
            
            System.out.println("srs is type " + type);
            
            // Retrieve the concurrency mode of this ResultSet object. 
            // The concurrency used is determined by the Statement object 
            // that created the result set.
            // Could return one of the following 2
            //      -ResultSet.CONCUR_READ_ONLY
            //      -ResultSet.CONCUR_UPDATABLE
            int concur = srs.getConcurrency();
            
            System.out.println("srs has concurrency " + concur);
            while (srs.next()) {
                String name = srs.getString("COF_NAME");
                int id = srs.getInt("SUP_ID");
                float price = srs.getFloat("PRICE");
                int sales = srs.getInt("SALES");
                int total = srs.getInt("TOTAL");
                System.out.print(name + "   " + id + "   " + price);
                System.out.println("   " + sales + "   " + total);
            }
            
            srs.close();
            stmt.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
            System.err.println("SQLState:  " + ex.getSQLState());
            System.err.println("Message:  " + ex.getMessage());
            System.err.println("Vendor:  " + ex.getErrorCode());
        }
    }
}

