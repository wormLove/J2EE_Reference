/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

class PrintColumns  {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        String query = "select * from COFFEES";
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
            
            ResultSet rs = stmt.executeQuery(query);
            
            // Get the metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // Get and display Column type
            PrintColumnTypes.printColTypes(rsmd);
            System.out.println("");
            
            // Get the number of columns
            int numberOfColumns = rsmd.getColumnCount();
            
            // Display the column names
            for (int i = 1; i <= numberOfColumns; i++) {
                if (i > 1) System.out.print(",  ");
                String columnName = rsmd.getColumnName(i);
                System.out.print(columnName);
            }
            System.out.println("");
            
            // Display the rows of table
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }
            
            stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
    }
}

