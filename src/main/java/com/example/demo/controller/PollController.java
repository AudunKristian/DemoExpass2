package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.manager.PollManager;
import com.example.demo.model.Poll;

import java.util.Collection;

@RestController
@RequestMapping("/polls")
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        try {
            // Validate input (you may want to validate further based on your application logic)
            if (poll.getQuestion() == null || poll.getVoteOptions() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Poll createdPoll = pollManager.createPoll(
                poll.getCreator().getId(), // Assuming you need a userId to associate with the poll
                poll.getQuestion(),
                poll.getPublishedAt(),
                poll.getValidUntil(),
                poll.isPublic(),
                poll.getVoteOptions(),
                poll.getInvitedUsers()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPoll);
        } catch (IllegalArgumentException e) {
            // Handle specific cases, like invalid user or parameters
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Handle unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Poll>> getAllPolls() {
        Collection<Poll> polls = pollManager.getAllPolls();
        if (polls.isEmpty()) {
            return ResponseEntity.noContent().build(); // No content if no polls
        }
        return ResponseEntity.ok(polls);
    }
}
