package com.example.demo;

/* 
import com.example.demo.manager.PollManager;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoExpass2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PollManager pollManager; // Assuming you have a way to access the manager

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        // Reset state before each test
        pollManager.getAllUsers().clear();
        pollManager.getAllPolls().clear();
        user1 = new User("User1", "user1@example.com");
        user2 = new User("User2", "user2@example.com");
        pollManager.createUser(user1.getUsername(), user1.getEmail());
        pollManager.createUser(user2.getUsername(), user2.getEmail());
    }

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"User1\", \"email\": \"user1@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("User1"))
                .andExpect(jsonPath("$[1].username").value("User2"));
    }

    @Test
    public void testCreatePoll() throws Exception {
        // Create poll
        mockMvc.perform(post("/polls")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"question\": \"Favorite color?\", \"voteOptions\": [{\"caption\": \"Red\"}, {\"caption\": \"Blue\"}], \"isPublic\": true, \"invitedUsers\": []}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListPolls() throws Exception {
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].question").value("Favorite color?"));
    }

    @Test
    public void testVoteOnPoll() throws Exception {
        // Create a vote
        mockMvc.perform(post("/polls/1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", user2.getId())
                .content("{\"selectedOption\": {\"id\": \"1\"}}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testChangeVote() throws Exception {
        // Change the vote
        mockMvc.perform(post("/polls/1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", user2.getId())
                .content("{\"selectedOption\": {\"id\": \"2\"}}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListVotes() throws Exception {
        mockMvc.perform(get("/polls/1/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].selectedOption.id").value("2")); // Assuming user2's most recent vote
    }

    @Test
    public void testDeletePoll() throws Exception {
        mockMvc.perform(delete("/polls/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListVotesAfterDelete() throws Exception {
        mockMvc.perform(get("/polls/1/votes"))
                .andExpect(status().isNoContent());
    }

}

*/
