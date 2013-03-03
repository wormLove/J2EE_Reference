/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class ForeignKeysCoffees  {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        
        // Create COFFEESFK table which has SUP_ID foreign key field
        String createString = "create table COFFEESFK " +
                "(COF_NAME varchar(32) NOT NULL, " +
                "SUP_ID int, " +
                "PRICE float, " +
                "SALES int, " +
                "TOTAL int, " +
                "primary key(COF_NAME), " +
                "foreign key(SUP_ID) references SUPPLIERSPK)";
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
            
            ResultSet rs = dbmd.getImportedKeys(null, null, "COFFEESFK");
            while (rs.next()) {
                String pkTable = rs.getString("PKTABLE_NAME");
                String pkColName = rs.getString("PKCOLUMN_NAME");
                String fkTable = rs.getString("FKTABLE_NAME");
                String fkColName = rs.getString("FKCOLUMN_NAME");
                short updateRule = rs.getShort("UPDATE_RULE");
                short deleteRule = rs.getShort("DELETE_RULE");
                System.out.println("primary key table name :  " + pkTable);
                System.out.print("primary key column name :  ");
                System.out.println(pkColName);
                System.out.println("foreign key table name :  " + fkTable);
                System.out.print("foreign key column name :  ");
                System.out.println(fkColName);
                System.out.println("update rule:  " + updateRule);
                System.out.println("delete rule:  " + deleteRule);
                System.out.println("");
            }
            
            rs.close();
            stmt.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
    }
}

