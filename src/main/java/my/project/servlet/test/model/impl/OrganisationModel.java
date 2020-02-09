package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.domain.Organisation;

import java.util.List;

public interface OrganisationModel {


    boolean updateOrganisation(Organisation organisation);

    List<Organisation> getListOfOrg();

    boolean deleteOrg(Organisation organisation);

    boolean createOrg(Organisation organisation);

    Organisation getOrgById(int id);

}
