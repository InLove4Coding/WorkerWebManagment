package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.domain.Organisation;
import my.project.servlet.test.model.domain.Worker;
import my.project.servlet.test.service.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganisationModelImpl implements OrganisationModel {

    public final String ADD_ORGANISATION = "INSERT INTO organisation (name,address,director) VALUES(?,?,?)";
    public final String SELECT_ORGANISATION_BY_ID = "SELECT * FROM organisation WHERE id=?";
    public final String SELECT_ALL_ORGANISATION = "SELECT * FROM organisation";
    public final String DELETE_ORGANISATION = "DELETE FROM organisation WHERE id=?";
    public final String UPDATE_ORGANISATION = "UPDATE organisation SET address=? WHERE id=?";
    Connection connection;


    public OrganisationModelImpl() throws SQLException {
        connection = DataBaseConnection.getInstance().getConnection();

    }

    @Override
    public boolean updateOrganisation(Organisation organisation) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORGANISATION);
            String address = organisation.getAddress();
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, organisation.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Organisation> getListOfOrg() {
        List<Organisation> organisationList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORGANISATION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Worker worker = new Worker();
                Organisation organisation = new Organisation();
                organisation.setId(resultSet.getInt("id"));
                organisation.setName(resultSet.getString("name"));
                organisation.setAddress(resultSet.getString("address"));
                worker.setId(resultSet.getInt("director"));
                organisation.setDirector(worker);
                organisationList.add(organisation);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organisationList;
    }

    @Override
    public boolean deleteOrg(Organisation organisation) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORGANISATION);
            preparedStatement.setInt(1, organisation.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createOrg(Organisation organisation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORGANISATION);
            preparedStatement.setString(1, organisation.getName());
            preparedStatement.setString(2, organisation.getAddress());
            preparedStatement.setInt(3, organisation.getDirector().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @Override
    public Organisation getOrgById(int id) {
        Organisation organisation = new Organisation();
        Worker worker = new Worker();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORGANISATION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            organisation.setId(resultSet.getInt("id"));
            organisation.setAddress(resultSet.getString("address"));
            worker.setId(resultSet.getInt("director"));
            organisation.setDirector(worker);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organisation;
    }
}
