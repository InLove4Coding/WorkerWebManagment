package my.project.servlet.test.model;

import my.project.servlet.test.model.domain.Worker;

import java.util.List;


public interface WorkerModel {

    Worker getWorker(Worker worker);

    Worker getWorkerById(int i);

    boolean addWorkerToDB(Worker worker);

    boolean deleteWorker(Worker worker);

    boolean updateWorker(Worker worker);

    List<Worker> getListWorker();

}
