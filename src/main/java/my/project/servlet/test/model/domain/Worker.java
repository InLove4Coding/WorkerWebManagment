package my.project.servlet.test.model.domain;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Worker {

    int id;
    String surname;
    List<Position> positionList = new ArrayList<>();

    // Map <Organisation, Position> map = new HashMap<Organisation, Position>();


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

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public void addPositionToWorker(Position position) {
        positionList.add(position);
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("surname", surname);
        return obj;
    }
}
