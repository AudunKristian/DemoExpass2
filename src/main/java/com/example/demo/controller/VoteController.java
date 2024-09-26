package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.manager.PollManager; // Ensure this import is correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/polls/{pollId}/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<Vote> createVote(@PathVariable String pollId, @RequestBody Vote vote, @RequestParam String userId) {
        try {
            // Ensure that the vote request contains necessary information
            if (vote.getSelectedOption() == null || userId == null || userId.isEmpty()) {
                return ResponseEntity.badRequest().build(); // Bad request if data is incomplete
            }

            // Create a vote using the pollId, userId, and the selected option
            Vote createdVote = pollManager.castVote(userId, pollId, vote.getSelectedOption().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVote);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return bad request for invalid inputs
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Return forbidden if voting conditions aren't met
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle unexpected errors
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Vote>> getVotes(@PathVariable String pollId) {
        Collection<Vote> votes = pollManager.getAllVotes(); // Get all votes
        if (votes.isEmpty()) {
            return ResponseEntity.noContent().build(); // No content if no votes
        }
        return ResponseEntity.ok(votes); // Return the list of votes
    }
}
