package org.example.cloudbasedappshw1.httpclient.answer;

import java.util.List;

public class Dimensions {

    public List<Object> dataSet;
    public List<Object> series;
    List<Observation> observation;

    // Getters and setters
    public List<Object> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Object> dataSet) {
        this.dataSet = dataSet;
    }

    public List<Object> getSeries() {
        return series;
    }

    public void setSeries(List<Object> series) {
        this.series = series;
    }

    public List<Observation> getObservation() {
        return observation;
    }

    public void setObservation(List<Observation> observation) {
        this.observation = observation;
    }
}
