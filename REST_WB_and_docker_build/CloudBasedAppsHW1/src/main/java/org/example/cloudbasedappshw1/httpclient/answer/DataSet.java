package org.example.cloudbasedappshw1.httpclient.answer;

import java.util.List;
import java.util.Map;

public class DataSet {

    private String action;

    private Map<String, List<Object>> observations;

    //Getters and setters

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, List<Object>> getObservations() {
        return observations;
    }

    public void setObservations(Map<String, List<Object>> observations) {
        this.observations = observations;
    }
}
