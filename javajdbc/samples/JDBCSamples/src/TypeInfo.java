/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.net.URL;
import java.sql.*;

public class TypeInfo {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        DatabaseMetaData dbmd;
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        try {
            con = DriverManager.getConnection(url,
                    "app", "app");
            
            dbmd = con.getMetaData();
            
            ResultSet rs = dbmd.getTypeInfo();
            while (rs.next()) {
                String typeName = rs.getString("TYPE_NAME");
                short dataType = rs.getShort("DATA_TYPE");
                String createParams = rs.getString("CREATE_PARAMS");
                int nullable = rs.getInt("NULLABLE");
                boolean caseSensitive = rs.getBoolean("CASE_SENSITIVE");
                System.out.println("DBMS type " + typeName + ":");
                System.out.println("     java.sql.Types:  "  + dataType);
                System.out.print("     parameters used to create: ");
                System.out.println(createParams);
                System.out.println("     nullable?:  "  + nullable);
                System.out.print("     case sensitive?:  ");
                System.out.println(caseSensitive);
                System.out.println("");
                
            }
            
            con.close();
            
            
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}

