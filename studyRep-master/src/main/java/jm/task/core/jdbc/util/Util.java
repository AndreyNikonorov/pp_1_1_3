package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Util {
   static String url = "jdbc:mysql://localhost:3306/katadb";
   static String username = "root";
    static String password = "root";

    public static  Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e) {
            System.out.println("Connection Failed");
        }

    return connection;

    }

}