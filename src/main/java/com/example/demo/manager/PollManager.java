package com.example.demo.manager;
import org.springframework.stereotype.Component;

import com.example.demo.model.Poll;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.model.VoteOption;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Poll> polls = new HashMap<>();
    private final Map<String, VoteOption> voteOptions = new HashMap<>();
    private final Map<String, Vote> votes = new HashMap<>();

    public User createUser(String username, String email) {
        User user = new User(username, email);
        users.put(username, user);
        return user;
    }

    public Poll createPoll(String question, Instant publishedAt, Instant validUntil) {
        Poll poll = new Poll(question, publishedAt, validUntil);
        polls.put(poll.getId(), poll); // Assume Poll has a method getId()
        return poll;
    }

    public VoteOption createVoteOption(String caption, int presentationOrder) {
        VoteOption option = new VoteOption(caption, presentationOrder);
        voteOptions.put(option.getId(), option); // Assume VoteOption has a method getId()
        return option;
    }

    public Vote castVote(String userId, String voteOptionId) {
        Vote vote = new Vote(Instant.now());
        votes.put(vote.getId(), vote); // Assume Vote has a method getId()
        return vote;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public Collection<Poll> getAllPolls() {
        return polls.values();
    }

    public Collection<VoteOption> getAllVoteOptions() {
        return voteOptions.values();
    }

    public Collection<Vote> getAllVotes() {
        return votes.values();
    }
}
