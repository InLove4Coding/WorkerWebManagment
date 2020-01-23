package my.project.servlet.test.model;
import my.project.servlet.test.model.domain.Worker;


public interface WorkerModel {

    Worker getWorkerByID(int id);

    boolean addWorkerToDB(Worker worker);

}
