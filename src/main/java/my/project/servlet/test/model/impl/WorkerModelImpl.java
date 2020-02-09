package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.WorkerModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class WorkerModelImpl implements WorkerModel {

    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    public final String SELECT_WORKER_BY_ID = "SELECT * FROM workers WHERE id=?";
    public final String DELETE_WORKER_BY_SURNAME = "DELETE FROM workers WHERE surname=?";
    public final String ADD_WORKER = "INSERT INTO workers(`surname`,position,salary) VALUES(?,?,?)";
    public final String UPDATE_WORKER_SALARY_BY_SURNAME = "UPDATE workers SET salary=? WHERE surname=?";
    Connection connection;

    public WorkerModelImpl() {
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


}


