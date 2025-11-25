package org.example.quizweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    //Connector amb la base de dades
    public static String url = "jdbc:mysql://db:3306/quiz";
    public static String user = "root";
    public static String password = "root";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }   catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}