package com.example.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class User {
    private String id;  
    private String username;
    private String email;

    
    @JsonManagedReference // Prevent cyclic dependency with Poll
    private List<Poll> pollsCreated;

    @JsonManagedReference // Prevent cyclic dependency with Vote
    private List<Vote> votes;
    
    
    public User() {}


    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
