package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.manager.PollManager; // Ensure this import is correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List; // Add this import for List

@RestController
@RequestMapping("/polls/{pollId}/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<Vote> createVote(@PathVariable String pollId, @RequestBody Vote vote) {
        Vote createdVote = pollManager.castVote(pollId);
        return ResponseEntity.ok(createdVote);
    }

    @GetMapping
    public ResponseEntity<Collection<Vote>> getVotes(@PathVariable String pollId) {
        return ResponseEntity.ok(pollManager.getAllVotes());
    }
}
