package my.project.servlet.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection dataBaseConnection;
    Connection connection;
    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    private DataBaseConnection() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println();
                System.out.println("база данных подключена" + "\n");
            }
        } catch (SQLException e) {
            System.out.println("Подключение к Базе Данных сорвалось");


        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
        } else if (dataBaseConnection.getConnection().isClosed()) {
            dataBaseConnection = new DataBaseConnection();
        }

        return dataBaseConnection;
    }
}

