package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.WorkerModel;
import my.project.servlet.test.service.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class WorkerModelImpl implements WorkerModel {

    public final String SELECT_WORKER_BY_ID = "SELECT * FROM workers WHERE id=?";
    public final String DELETE_WORKER_BY_SURNAME = "DELETE FROM workers WHERE surname=?";
    public final String ADD_WORKER = "INSERT INTO workers(`surname`,position,salary) VALUES(?,?,?)";
    public final String UPDATE_WORKER_SALARY_BY_SURNAME = "UPDATE workers SET salary=? WHERE surname=?";
    Connection connection;

    public WorkerModelImpl() throws SQLException {
        Connection connection = DataBaseConnection.getInstance().getConnection();


    }


}


