package my.project.servlet.test.model.impl;

import my.project.servlet.test.model.domain.Organisation;
import my.project.servlet.test.model.domain.Position;
import my.project.servlet.test.model.domain.Worker;

import java.util.List;

public interface PositionModel {

    boolean updatePosition(Position position);

    List<Position> getListOfPos();

    boolean deletePos(Position position);

    boolean createPos(Position position);

    List<Position> byOrganisation(Organisation organisation);

    List<Position> byWorkerID(Worker worker);

}
