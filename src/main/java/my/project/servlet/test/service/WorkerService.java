package my.project.servlet.test.service;

import my.project.servlet.test.model.WorkerModel;
import my.project.servlet.test.model.domain.Worker;
import my.project.servlet.test.model.impl.WorkerModelImpl;


public class WorkerService {

    private WorkerModel workerModel = new WorkerModelImpl();

    public Worker getWorkerByID(int id) {

        return workerModel.getWorkerByID(id);
    }

    public boolean addWorkerToDB(Worker worker) {

        return workerModel.addWorkerToDB(worker);
    }

}