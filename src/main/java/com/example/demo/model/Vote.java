package com.example.demo.model;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Vote {
    private String id; // Unique identifier for the vote
    private Instant publishedAt; // Timestamp of when the vote was cast

    @JsonBackReference // Prevent recursion when serializing User
    private User voter; // Reference to the voting user

    @JsonBackReference // Prevent recursion when serializing Poll
    private Poll poll; // Reference to the poll

    @JsonBackReference // Prevent recursion when serializing VoteOption
    private VoteOption selectedOption; // The option chosen by the user

    // Constructor with UUID and relationships
    public Vote(String id, Instant publishedAt, User voter, Poll poll, VoteOption selectedOption) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.voter = voter;
        this.poll = poll;
        this.selectedOption = selectedOption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public VoteOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(VoteOption selectedOption) {
        this.selectedOption = selectedOption;
    }
}
