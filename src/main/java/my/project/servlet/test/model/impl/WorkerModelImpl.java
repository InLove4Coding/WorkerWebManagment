package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.WorkerModel;
import my.project.servlet.test.model.domain.Worker;

import java.sql.*;

public class WorkerModelImpl implements WorkerModel {
    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    public final String SELECT_WORKER_BY_ID = "SELECT * FROM workers WHERE id=?";
    //  public static final String ADD_NEW_WORKER = "INSERT INTO workers VALUES (?,?,?,?,?)";
    public static final String ADD_WORKER = "INSERT INTO workers(`surname`) VALUES(?)";
    Connection connection;

     public WorkerModelImpl()  {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return worker;

    }

    public boolean addWorkerToDB(Worker worker) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_WORKER);
            statement.setString(1, worker.getSurname());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
