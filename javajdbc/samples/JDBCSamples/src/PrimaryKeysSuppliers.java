/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class PrimaryKeysSuppliers  {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        String createString = "create table SUPPLIERSPK " +
                "(SUP_ID INTEGER NOT NULL, " +
                "SUP_NAME VARCHAR(40), " +
                "STREET VARCHAR(40), " +
                "CITY VARCHAR(20), " +
                "STATE CHAR(2), " +
                "ZIP CHAR(5), " +
                "primary key(SUP_ID))";
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
            
            stmt  = con.createStatement();
            stmt.executeUpdate(createString);
            
            DatabaseMetaData dbmd = con.getMetaData();
            
            ResultSet rs = dbmd.getPrimaryKeys(null, null, "SUPPLIERSPK");
            while (rs.next()) {
                String name = rs.getString("TABLE_NAME");
                String columnName = rs.getString("COLUMN_NAME");
                String keySeq = rs.getString("KEY_SEQ");
                String pkName = rs.getString("PK_NAME");
                System.out.println("table name :  " + name);
                System.out.println("column name:  " + columnName);
                System.out.println("sequence in key:  " + keySeq);
                System.out.println("primary key name:  " + pkName);
                System.out.println("");
            }
            
            rs.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
    }
}

