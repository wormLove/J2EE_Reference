/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */

import java.sql.*;

public class GetParamMetaData {
    
    public static void main(String args[]) {
        
        String url = "jdbc:derby://localhost:1527/sample";
        
        Connection con;
        PreparedStatement pstmt;
        ParameterMetaData pmd;
        
        // SQL command with 2 parameters
        String sql = "UPDATE COFFEES SET SALES = ? " +
                "WHERE COF_NAME = ?";
        
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        
        try {
            
            con = DriverManager.getConnection(url,
                    "app", "app");
            
            // Create a prepared statement
            pstmt = con.prepareStatement(sql);
            
            // Retrieve the number, types and properties of this 
            // PreparedStatement object's parameters
            pmd = pstmt.getParameterMetaData();
            
            // Retrieve the designated parameter's number of decimal digits
            int totalDigits = pmd.getPrecision(1);
            
            // Retrieve the designated parameter's number of digits 
            // to right of the decimal point
            int digitsAfterDecimal = pmd.getScale(1);
            
            // Retrieve whether values for the designated parameter 
            // can be signed numbers
            boolean b = pmd.isSigned(1);
            
            System.out.println("The first parameter ");
            System.out.println("    has precision " + totalDigits);
            System.out.println("    has scale " + digitsAfterDecimal);
            System.out.println("    may be a signed number " + b);
            
            // Retrieve the number of parameters in the PreparedStatement 
            // object for which this ParameterMetaData object contains information.
            int count = pmd.getParameterCount();
            System.out.println("count is " + count);
            
            for (int i = 1; i <= count; i++) {
                int type = pmd.getParameterType(i);
                String typeName = pmd.getParameterTypeName(i);
                System.out.println("Parameter " + i + ":");
                System.out.println("    type is " + type);
                System.out.println("    type name is " + typeName);
            }
            
            pstmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}
