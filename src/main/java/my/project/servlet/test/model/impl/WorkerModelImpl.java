package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.WorkerModel;
import my.project.servlet.test.model.domain.Worker;

import java.sql.*;

public class WorkerModelImpl implements WorkerModel {
    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    public final String SELECT_WORKER_BY_ID = "SELECT * FROM workers WHERE id=?";
    // i need it to expand my project    public final String SELECT_WORKER_BY_ID = "SELECT * FROM workers as w JOIN organisation_workers as o WHERE id=?";
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

    public Worker getWorkerByID(int id) {

        Worker worker = new Worker();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_WORKER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int i = resultSet.getInt("id");
            worker.setId(resultSet.getInt("id"));
            String s = resultSet.getString("surname");
            worker.setSurname(resultSet.getString("surname"));
            String p = resultSet.getString("position");
            worker.setPosition(resultSet.getString("position"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return worker;

    }

    public boolean addWorkerToDB(Worker worker) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_WORKER);
            statement.setString(1, worker.getSurname());
            statement.setString(2, worker.getPosition());
            statement.setInt(3, worker.getSalary());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteWorkerBySurname(String surname) {

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_WORKER_BY_SURNAME);
            statement.setString(1, surname);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean updateWorkerSalaryBySurname(String surname, int salary) {

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_WORKER_SALARY_BY_SURNAME);
            statement.setInt(1, salary);
            statement.setString(2, surname);
            statement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
