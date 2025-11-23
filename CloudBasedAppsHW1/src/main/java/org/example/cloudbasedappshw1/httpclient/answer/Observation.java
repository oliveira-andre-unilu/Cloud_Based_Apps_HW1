package org.example.cloudbasedappshw1.httpclient.answer;

import java.util.List;

public class Observation {
    private String id;
    private String name;
    private int keyPosition;
    private String role;
    private List<ObservationValue> values;

    //Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyPosition() {
        return keyPosition;
    }

    public void setKeyPosition(int keyPosition) {
        this.keyPosition = keyPosition;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ObservationValue> getValues() {
        return values;
    }

    public void setValues(List<ObservationValue> values) {
        this.values = values;
    }
}
