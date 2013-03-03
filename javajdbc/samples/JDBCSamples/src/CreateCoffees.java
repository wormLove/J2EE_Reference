/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class CreateCoffees {
   
    public static void main(String args[]) {
       
        // Define the url for the sample database
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        String createString;
        createString = "create table COFFEES " +
                "(COF_NAME varchar(32), " +
                "SUP_ID int, " +
                "PRICE float, " +
                "SALES int, " +
                "TOTAL int)";
        Statement stmt;
       
        try {
            // Load database driver. This is old method of loading
            // database driver.
            Class.forName("org.apache.derby.jdbc.ClientDriver");
           
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
       
        try {
            // Create database connection
            con = DriverManager.getConnection(url,
                    "app", "app");
           
            // Create a statement and execute it
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
           
            // If there is no exception, the COFFEES table must be successfully created
            System.out.println("COFFEES table is successfully created");
            
            // Close statement and connection
            stmt.close();
            con.close();
           
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}