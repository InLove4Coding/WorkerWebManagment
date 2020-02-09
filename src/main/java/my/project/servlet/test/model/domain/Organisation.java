package my.project.servlet.test.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Organisation {

    private int id;
    private String name;
    private String address;
    private Worker director;
    private List<Position> list = new ArrayList<Position>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Worker getDirector() {
        return director;
    }

    public void setDirector(Worker director) {
        this.director = director;
    }

    public List<Position> getList() {
        return list;
    }

    public void setList(List<Position> list) {
        this.list = list;
    }

    public void addWorkerToOrganisation(Position position) {
        list.add(position);
    }

}
