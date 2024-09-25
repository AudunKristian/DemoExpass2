package com.example.pollapp.controller;

import com.example.pollapp.model.VoteOption;
import com.example.pollapp.manager.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
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
        VoteOption createdVoteOption = pollManager.addVoteOption(pollId, voteOption);
        return ResponseEntity.ok(createdVoteOption);
    }

    @GetMapping
    public ResponseEntity<List<VoteOption>> getVoteOptions(@PathVariable String pollId) {
        return ResponseEntity.ok(pollManager.getVoteOptions(pollId));
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable String pollId, @PathVariable String optionId) {
        pollManager.deleteVoteOption(pollId, optionId);
        return ResponseEntity.noContent().build();
    }
}
