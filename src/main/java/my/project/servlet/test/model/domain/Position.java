package my.project.servlet.test.model.domain;

public class Position {
    Worker worker;
    Organisation organisation;
    String position;
    int salary;


    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;


    }
}
