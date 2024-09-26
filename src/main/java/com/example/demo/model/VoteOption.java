package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class VoteOption {
    private String id; //Unique identifier for the vote option
    private String caption;
    private int presentationOrder; // Display order of the option

    @JsonBackReference // Prevent recursion when serializing Poll
    private Poll poll; // Reference to the parent Poll

    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
