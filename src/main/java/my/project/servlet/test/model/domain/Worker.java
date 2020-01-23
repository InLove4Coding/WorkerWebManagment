package my.project.servlet.test.model.domain;

import org.json.simple.JSONObject;

public class Worker {

    int id;
    String surname;

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

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("surname", surname);
        return obj;
    }
}
