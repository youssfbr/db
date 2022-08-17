package com.github.youssfbr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static void main(String[] args) {

        String urlConnection = "jdbc:mysql://localhost/dio";

        try (Connection conn = DriverManager.getConnection(urlConnection, "root", "1234")) {
            System.out.println("SUCCESS!");
        } catch (SQLException e) {
            System.out.println("FAIL!");
            throw new RuntimeException(e);
        }

    }
}
