package org.example.cloudbasedappshw1.httpclient.answer;

import java.util.List;

public class Answer {
    private Header header;
    private List<DataSet> dataSets;
    private Structure structure;

    //Getters and setters

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
