package com.example.JDBCconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/employeeleavetracker";
    private static final String USER = "root";
    private static final String PASSWORD = "sharath123";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
