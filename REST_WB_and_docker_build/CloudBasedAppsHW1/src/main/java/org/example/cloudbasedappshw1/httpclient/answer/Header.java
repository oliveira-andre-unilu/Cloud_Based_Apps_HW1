package org.example.cloudbasedappshw1.httpclient.answer;

public class Header {
    private String id;
    private String prepared;
    private boolean test;
    private Sender sender;

    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrepared() {
        return prepared;
    }

    public void setPrepared(String prepared) {
        this.prepared = prepared;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
