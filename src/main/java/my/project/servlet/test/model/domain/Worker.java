package my.project.servlet.test.model.domain;

import org.json.simple.JSONObject;

public class Worker {

    int id;
    int salary;
    String surname;
    String position;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("surname", surname);
        obj.put("salary", salary);
        obj.put("position", position);
        return obj;
    }
}
