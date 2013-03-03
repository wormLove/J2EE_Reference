/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class DropCoffees {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        String dropString = "drop table COFFEES";
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
            
            stmt = con.createStatement();
            stmt.executeUpdate(dropString);
            
            System.out.println("COFFEES table is successfully dropped");
            stmt.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}

