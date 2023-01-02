package JDBC;

import java.sql.*;

//public class SQL_Connection {
//    public static void main(String[] args) {
//        try {
//            //load the driver
//            Class.forName("com.mysql.jdbc.Driver");
//            //create the connection object
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
//            //create the statement object
//            Statement stmt = con.createStatement();
//            //execute query
//            ResultSet rs = stmt.executeQuery("select * from emp");
//            while (rs.next())
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            //close the connection object
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
public class SQL_Connection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            Connection connection = DriverManager.getConnection(url, "root", "Ritesh@2001");
            System.out.println("Connection Established\n");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from city");
            rs.next();
            while (rs.next()) {
                System.out.println(rs.getArray(2));
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
