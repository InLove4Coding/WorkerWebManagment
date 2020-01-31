package my.project.servlet.test.controller;

import my.project.servlet.test.model.domain.Worker;
import my.project.servlet.test.model.domain.WorkerVM;
import my.project.servlet.test.service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/get_worker")
public class WorkerController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String jsonString = req.getHeader("add");
        PrintWriter writer = resp.getWriter();
        WorkerService workerService = new WorkerService();
        WorkerVM workerVM = new WorkerVM(jsonString);
        if (jsonString != null) {
            Worker worker = new Worker();
            worker.setSurname(workerVM.getSurname());
            worker.setPosition(workerVM.getPosition());
            worker.setSalary(workerVM.getSalary());
            workerService.addWorkerToDB(worker);
            writer.println("ok");
        } else writer.println("insert parameter,pls");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String jsonString = req.getHeader("getById");
        PrintWriter writer = resp.getWriter();
        WorkerService workerService = new WorkerService();
        WorkerVM workerVM = new WorkerVM(jsonString);

        if (jsonString != null) {
            Worker worker = workerService.getWorkerByID(workerVM.getId());
            String result = worker.toJson().toJSONString();
            writer.println(result);
            writer.close();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = req.getHeader("delete");
        PrintWriter writer = resp.getWriter();

        if (jsonString != null) {
            WorkerVM workerVM = new WorkerVM(jsonString);
            Worker worker = new Worker();
            worker.setSurname(workerVM.getSurname());
            WorkerService workerService = new WorkerService();
            if (workerService.deleteWorkerBySurname(worker.getSurname()) == true) {
                writer.println("Deleted worker:" + worker.getSurname());
            } else {
                writer.println("You dont input something");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = req.getHeader("update");
        WorkerVM workerVM = new WorkerVM(jsonString);
        Worker worker = new Worker();
        worker.setSurname(workerVM.getSurname());
        worker.setSalary(workerVM.getSalary());
        PrintWriter writer = resp.getWriter();

        if (workerVM.isCorrect()) {

            WorkerService workerService = new WorkerService();
            if (workerService.updateWorkerSalaryBySurname(workerVM.getSurname(), workerVM.getSalary()) == true) {
                writer.println("Write is ok");
            } else writer.println("Fail to write a DataBase");

        }


    }


}
