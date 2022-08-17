package com.github.youssfbr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCSB {

    public static void main(String[] args) {

        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "dio";
        String user = "root";
        String password = "1234";

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = sb.toString(); //sb.toString() == "jdbc:mysql://dio"

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("SUCESSO ao se conectar ao banco MySQL!");
        } catch (SQLException e) {
            System.out.println("FALHA ao se conectar ao banco MySQL!");
            e.printStackTrace();
        }

    }
}
