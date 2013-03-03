/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class TransactionPairs {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con = null;
        Statement stmt;
        PreparedStatement updateSales;
        PreparedStatement updateTotal;
        String updateString = "update COFFEES " +
                "set SALES = ? where COF_NAME = ?";
        
        String updateStatement = "update COFFEES " +
                "set TOTAL = TOTAL + ? where COF_NAME = ?";
        String query = "select COF_NAME, SALES, TOTAL from COFFEES";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        try {
            
            con = DriverManager.getConnection(url,
                    "app", "app");
            
            updateSales = con.prepareStatement(updateString);
            updateTotal = con.prepareStatement(updateStatement);
            int [] salesForWeek = {175, 150, 60, 155, 90};
            String [] coffees = {"Colombian", "French_Roast",
            "Espresso", "Colombian_Decaf",
            "French_Roast_Decaf"};
            int len = coffees.length;
            
            // Sets this connection's auto-commit mode to fase. Tts SQL statements 
            // are grouped into transactions that are terminated by a call to either 
            // the method commit or the method rollback. By default, new connections 
            // are in auto-commit mode.
            con.setAutoCommit(false);
            
            for (int i = 0; i < len; i++) {
                updateSales.setInt(1, salesForWeek[i]);
                updateSales.setString(2, coffees[i]);
                updateSales.executeUpdate();
                
                updateTotal.setInt(1, salesForWeek[i]);
                updateTotal.setString(2, coffees[i]);
                updateTotal.executeUpdate();
                con.commit();
            }
            
            // Sets this connection's auto-commit mode to true. 
            // If a connection is in auto-commit mode, then all its SQL statements 
            // will be executed and committed as individual transactions. 
            con.setAutoCommit(true);
            
            updateSales.close();
            updateTotal.close();
            
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String c = rs.getString("COF_NAME");
                int s = rs.getInt("SALES");
                int t = rs.getInt("TOTAL");
                System.out.println(c + "     " +  s + "    " + t);
            }
            
            stmt.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            if (con != null) {
                try {
                    System.err.print("Transaction is being ");
                    System.err.println("rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    System.err.print("SQLException: ");
                    System.err.println(excep.getMessage());
                }
            }
        }
    }
}

