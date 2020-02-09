package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.domain.Organisation;
import my.project.servlet.test.model.domain.Position;
import my.project.servlet.test.model.domain.Worker;
import my.project.servlet.test.service.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionModelImpl implements PositionModel {

    public final String SELECT_POSITION_BY_WORKER_ID = "SELECT position FROM organisation_workers WHERE worker_id=?";
    public final String SELECT_POSITION_BY_ORGANISATION_ID = "SELECT position FROM organisation_workers WHERE organisation_id=?";
    public final String SELECT_ALL_POSITION = "SELECT distinct position FROM organisation_workers";
    public final String DELETE_POSITION = "DELETE FROM organisation_workers WHERE position=?";
    public final String UPDATE_POSITION = "UPDATE organisation_workers SET position=?,salary=? WHERE worker_id=? and organisation_id=?";
    Connection connection;

    public PositionModelImpl() throws SQLException {
        connection = DataBaseConnection.getInstance().getConnection();


    }


    @Override
    public boolean updatePosition(Position position) {

        if (position.getOrganisation().getId() == position.getWorker().getId()) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POSITION);
                preparedStatement.setString(1, position.getPosition());
                preparedStatement.setInt(2, position.getSalary());
                preparedStatement.setInt(3, position.getWorker().getId());
                preparedStatement.setInt(4, position.getOrganisation().getId());
                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public List<Position> getListOfPos() {
        Position position = new Position();
        List<Position> positionList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_POSITION);
            while (resultSet.next()) {
                position.setPosition(resultSet.getString("position"));
                positionList.add(position);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList;
    }

    @Override
    public boolean deletePos(Position position) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POSITION);
            preparedStatement.setString(1, position.getPosition());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createPos(Position position) {

        return false;
    }

    @Override
    public List<Position> byOrganisation(Organisation organisation) {
        List<Position> positionList = new ArrayList<>();
        Position position = new Position();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POSITION_BY_ORGANISATION_ID);
            preparedStatement.setInt(1, organisation.getId());
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                position.setPosition(resultSet.getString("position"));
                positionList.add(position);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return positionList;
    }

    @Override
    public List<Position> byWorkerID(Worker worker) {
        List<Position> positionList = new ArrayList<>();
        Position position = new Position();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POSITION_BY_WORKER_ID);
            preparedStatement.setInt(1, worker.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                position.setPosition(resultSet.getString("position"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList;
    }
}
