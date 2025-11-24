package org.example.cloudbasedappshw1.httpclient.answer;

import java.util.List;

public class Structure {

    private String name;
    private String description;
    private Dimensions dimensions;
    private Attributes attributes;
    private List<Object> annotations;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public List<Object> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Object> annotations) {
        this.annotations = annotations;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
