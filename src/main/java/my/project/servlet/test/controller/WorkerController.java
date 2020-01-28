package my.project.servlet.test.controller;

import my.project.servlet.test.model.domain.Worker;
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

    private WorkerService workerService = new WorkerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getHeader("xSurname");
        PrintWriter writer = resp.getWriter();

        if (parameter != null) {
            Worker worker = new Worker();
            worker.setSurname(parameter);
            workerService.addWorkerToDB(worker);
            writer.println("ok");
        } else writer.println("insert parameter,pls");
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String parameter = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        if (parameter != null) {
            try {
                id = Integer.parseInt(parameter);
            } catch (Exception e) {
                writer.println("id must be Integer");
            }
        } else {
            writer.println("id not found, set id");
        }

        Worker worker = workerService.getWorkerByID(id);
        String result = worker.toJson().toJSONString();
        writer.println(result);
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getHeader("dSurname");
        PrintWriter writer = resp.getWriter();

        if (parameter != null) {
            WorkerService workerService = new WorkerService();
            workerService.deleteWorkerBySurname(parameter);
            writer.println("Delete worker:" + parameter);
        } else {
            writer.println("Вы ничего не ввели");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getHeader("enterSurname");
        String salary = req.getHeader("updateSalary");
        int sal = 0;
        PrintWriter writer = resp.getWriter();

        if (surname != null && salary != null) {
            sal = Integer.parseInt(salary);
            WorkerService workerService = new WorkerService();
            if (workerService.updateWorkerSalaryBySurname(surname, sal) == true) {
                writer.println("Зарплата " + surname + " теперь равна: " + sal);
            } else writer.println("Запись в бд не удалась");

        }


    }


}
