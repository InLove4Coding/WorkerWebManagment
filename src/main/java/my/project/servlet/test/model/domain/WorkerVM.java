package my.project.servlet.test.model.domain;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WorkerVM {

    private int id;
    private int salary;
    private String surname;
    private String position;

    public WorkerVM(String string) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(string);
            JSONObject jsonObject = (JSONObject) obj;
            if (jsonObject.get("id") != null) {
                id = Integer.valueOf((String) jsonObject.get("id"));
            }
            if (jsonObject.get("surname") != null) {
                surname = (String) jsonObject.get("surname");
            }
            if (jsonObject.get("salary") != null) {
                salary = Integer.valueOf((String) jsonObject.get("salary"));
            }
            if (jsonObject.get("position") != null) {
                position = (String) jsonObject.get("position");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean isCorrect() {
        if (surname != null && surname != "" && salary > 0)
            return true;
        else return false;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
