/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class TableTypes  {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        try {
            con = DriverManager.getConnection(url,
                    "app", "app");
            
            DatabaseMetaData dbmd = con.getMetaData();
            String dbmsName = dbmd.getDatabaseProductName();
            ResultSet rs = dbmd.getTableTypes();
            System.out.print("The following types of tables are ");
            System.out.println("available in " + dbmsName + ":  ");
            
            while (rs.next()) {
                String tableType = rs.getString("TABLE_TYPE");
                System.out.println("    " + tableType);
            }
            
            rs.close();
            con.close();
            
        } catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
    }
}

