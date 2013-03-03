
import java.sql.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wjlwyz
 */
public class MyOwnJDBCProgram {
     public static void main(String args[]) throws SQLException {
        String url = "jdbc:derby://localhost:1527/sample";
        Connection con;
        String createString;
                createString =
                "create table LOCATION" +
                "(LOCATION_ID INTEGER NOT NULL PRIMARY KEY," +
                "LOCATION  VARCHAR(40))";

        String createString2;
                createString2 =
                "create table CD" +
                "(CD_ID INTEGER NOT NULL PRIMARY KEY," +
                "TITLE VARCHAR(40)," +
                "AUTHOR VARCHAR(40)," +
                "YEAR_CREATED INTEGER," +
                "LOCATION INTEGER," +
                "RATING INTEGER," +
                "CONSTRAINT FK_CD_LOCATION FOREIGN KEY (LOCATION) REFERENCES LOCATION(LOCATION_ID))";

        String query = "select * from CD";
        String query2 = "select * from LOCATION";

        Statement stmt;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(ex.getMessage());
        }


        try {
            // Create database connection
            con = DriverManager.getConnection(url,
                    "app", "app");

            // Create a statement and execute it
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
            stmt.executeUpdate(createString2);



            // If there is no exception, the COFFEES table must be successfully created
            System.out.println("LOCATION table is successfully created");
            System.out.println("CD table is successfully created");

            stmt.executeUpdate("INSERT INTO LOCATION VALUES (0,'Cha Cha Cha bar')");
            stmt.executeUpdate("INSERT INTO LOCATION VALUES (1,'Limon Restaurant')");
            stmt.executeUpdate("INSERT INTO LOCATION VALUES (2,'Taqueria el Balazo')");
            stmt.executeUpdate("INSERT INTO LOCATION VALUES (3,'Office')");
            stmt.executeUpdate("INSERT INTO LOCATION VALUES (4,'Home')");

            stmt.executeUpdate("INSERT INTO CD VALUES (0,    'Rock and Roll Aint Noise Pollution',  "
                    + " 'AC/DC',    1980,    0,    9)");
            stmt.executeUpdate("INSERT INTO CD VALUES (1,    'Shake a Leg',   "
                    + " 'AC/DC',    1980,    2,    6)");
            stmt.executeUpdate("INSERT INTO CD VALUES (2,    'Have a Drink on Me',  "
                    + " 'AC/DC',    1980,    2,    5)");
            stmt.executeUpdate("INSERT INTO CD VALUES (3,    'You Shook Me All Night Long', "
                    + " 'AC/DC',    1980,    1,    8)");
            stmt.executeUpdate("INSERT INTO CD VALUES (4,    'Back in Black', "
                    + " 'AC/DC',    1980,    4,    9)");
            stmt.executeUpdate("INSERT INTO CD VALUES (5,    'Let Me Put My Love into You', "
                    + " 'AC/DC',    1980,    4,    9)");


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

            ResultSet rs2 = stmt.executeQuery(query2);
            // Get the metadata
            ResultSetMetaData rsmd2 = rs2.getMetaData();

            // Get and display Column type
            PrintColumnTypes.printColTypes(rsmd2);
            System.out.println("");

            // Get the number of columns
            int numberOfColumns2 = rsmd2.getColumnCount();

            // Display the column names
            for (int j = 1; j <= numberOfColumns2; j++) {
                if (j > 1) System.out.print(",  ");
                String columnName2 = rsmd2.getColumnName(j);
                System.out.print(columnName2);
            }
            System.out.println("");

            // Display the rows of table
            while (rs2.next()) {
                for (int j = 1; j <= numberOfColumns2; j++) {
                    if (j > 1) System.out.print(",  ");
                    String columnValue2 = rs2.getString(j);
                    System.out.print(columnValue2);
                }
                System.out.println("");
            }

            // Close statement and connection
            stmt.close();
            con.close();

        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
     }

}
