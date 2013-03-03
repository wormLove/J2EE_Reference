/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class ScrollableResultSet {
    
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
            
            // ResultSet object that is scrollable and generally sensitive 
            // to changes made by others.
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            ResultSet srs = stmt.executeQuery("SELECT * FROM COFFEES");
            
            // Moves the cursor to the given row number in this ResultSet object.
            // If the row number is positive, the cursor moves to the given row 
            // number with respect to the beginning of the result set. The first 
            // row is row 1, the second is row 2, and so on.  
            srs.absolute(4);
            
            // Retrieves the current row number. 
            // The first row is number 1, the second number 2, and so on.
            int rowNum = srs.getRow(); // rowNum should be 4
            System.out.println("rowNum should be 4 " + rowNum);
            
            // If the given row number is negative, the cursor moves to an absolute 
            // row position with respect to the end of the result set. For example, 
            // calling the method absolute(-1) positions the cursor on the last row; 
            // calling the method absolute(-2) moves the cursor to the next-to-last 
            // row, and so on. 
            srs.relative(-3);
            rowNum = srs.getRow(); // rowNum should be 1
            System.out.println("rowNum should be 1 " + rowNum);
            
            srs.relative(2);
            rowNum = srs.getRow(); // rowNum should be 3
            System.out.println("rowNum should be 3 " + rowNum);
            
            srs.absolute(1);
            System.out.println("after last? " + srs.isAfterLast() );
            if (!srs.isAfterLast()) {
                String name = srs.getString("COF_NAME");
                float price = srs.getFloat("PRICE");
                System.out.println(name + "     " + price);
            }
            
            srs.afterLast();
            while (srs.previous()) {
                String name = srs.getString("COF_NAME");
                float price = srs.getFloat("PRICE");
                System.out.println(name + "     " + price);
            }
            
            srs.close();
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
            System.out.println("");
            
        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
            System.err.println("SQLState:  " + ex.getSQLState());
            System.err.println("Message:  " + ex.getMessage());
            System.err.println("Vendor:  " + ex.getErrorCode());
        }
    }
}

