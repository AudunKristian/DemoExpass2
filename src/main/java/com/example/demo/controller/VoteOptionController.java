package com.example.demo.controller;

import com.example.demo.model.VoteOption;
import com.example.demo.manager.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls/{pollId}/options")
public class VoteOptionController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<VoteOption> createVoteOption(@PathVariable String pollId, @RequestBody VoteOption voteOption) {
        try {
            // Validate that the vote option is not null or empty
            if (voteOption.getCaption() == null || voteOption.getCaption().isEmpty()) {
                return ResponseEntity.badRequest().body(null); // Bad request if the caption is missing
            }

            // Create the vote option using the pollId and the provided voteOption
            VoteOption createdVoteOption = pollManager.createVoteOption(pollId, voteOption.getCaption(), voteOption.getPresentationOrder());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVoteOption); // Return created status
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return bad request for invalid inputs
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle unexpected errors
        }
    }

    @GetMapping
    public ResponseEntity<List<VoteOption>> getVoteOptions(@PathVariable String pollId) {
        List<VoteOption> voteOptions = pollManager.getVoteOptions(pollId); // Retrieve the list of vote options
        if (voteOptions.isEmpty()) {
            return ResponseEntity.noContent().build(); // No content if there are no vote options
        }
        return ResponseEntity.ok(voteOptions); // Return the list of vote options
    }

}
