package my.project.servlet.test.model;

import my.project.servlet.test.model.domain.Organisation;
import my.project.servlet.test.model.domain.Position;
import my.project.servlet.test.model.domain.Worker;
import my.project.servlet.test.model.impl.OrganisationModel;
import my.project.servlet.test.model.impl.PositionModel;

import java.util.List;

public class Facade implements FacadeModel {
    private WorkerModel workerModel;
    private PositionModel positionModel;
    private OrganisationModel organisationModel;

    public Facade(WorkerModel workerModel, PositionModel positionModel, OrganisationModel organisationModel) {
        this.workerModel = workerModel;
        this.positionModel = positionModel;
        this.organisationModel = organisationModel;
    }


    boolean updateOrganisation(Organisation organisation) {
        return organisationModel.updateOrganisation(organisation);

    }

    List<Organisation> getListOfOrg() {

        return organisationModel.getListOfOrg();

    }

    boolean deleteOrg(Organisation organisation) {
        return organisationModel.deleteOrg(organisation);
    }

    boolean createOrg(Organisation organisation) {
        return organisationModel.createOrg(organisation);
    }

    Organisation getOrgById(int id) {
        return organisationModel.getOrgById(id);
    }

    public Worker getWorkerByID(int id) {
        Worker worker = workerModel.getWorkerById(id);
        List<Position> positionList = positionModel.byWorkerID(worker);
        for (Position pos : positionList
        ) {
            Organisation organisation = organisationModel.getOrgById(pos.getOrganisation().getId());
            Worker director = workerModel.getWorkerById(organisation.getDirector().getId());
            organisation.setDirector(director);
            pos.setOrganisation(organisation);
        }
        worker.setPositionList(positionList);
        return worker;
    }

    boolean addWorkerToDB(Worker worker) {
        return workerModel.addWorkerToDB(worker);

    }

    boolean deleteWorkerBySurname(Worker worker) {
        return workerModel.deleteWorker(worker);
    }

    boolean updateWorker(Worker worker) {
        return workerModel.updateWorker(worker);
    }

    boolean updateOrganisation(Position position) {
        return positionModel.updateOrganisation(position);
    }

    List<Position> getListOfPos() {
        return positionModel.getListOfPos();
    }

    boolean deletePos(Position position) {
        return positionModel.deletePos(position);
    }

    boolean createPos(Position position) {
        return positionModel.createPos(position);
    }

    List<Position> byOrganisation(Organisation organisation) {
        return positionModel.byOrganisation(organisation);
    }

    List<Position> byWorkerID(Worker worker) {
        return positionModel.byWorkerID(worker);
    }

}
