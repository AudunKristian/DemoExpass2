package com.example.demo.manager;

import org.springframework.stereotype.Component;
import com.example.demo.model.Poll;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.model.VoteOption;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class PollManager {
    
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Poll> polls = new HashMap<>();
    private final Map<String, VoteOption> voteOptions = new HashMap<>();
    private final Map<String, Vote> votes = new HashMap<>();

    // Create a new user and register them
    public User createUser(String username, String email) {
        User user = new User(username, email);
        users.put(user.getId(), user); // Assuming User has a unique ID getter
        return user;
    }

    // Create a new poll, linked to the poll creator (user)
    public Poll createPoll(String userId, String question, Instant publishedAt, Instant validUntil, boolean isPublic, List<VoteOption> voteOptions, List<User> invitedUsers) {
        User creator = users.get(userId);
        if (creator == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Create the new Poll object
        Poll poll = new Poll(UUID.randomUUID().toString(), question, publishedAt, validUntil, creator, voteOptions, null, invitedUsers);
        polls.put(poll.getId(), poll);
        return poll;
    }


    // Create a new vote option for a specific poll
    public VoteOption createVoteOption(String pollId, String caption, int presentationOrder) {
        Poll poll = polls.get(pollId);
        if (poll == null) {
            throw new IllegalArgumentException("Poll not found");
        }
        VoteOption option = new VoteOption(caption, presentationOrder);
        voteOptions.put(option.getId(), option);
        return option;
    }

    // Cast a vote

    public Vote castVote(String userId, String pollId, String voteOptionId) {
        User user = users.get(userId);
        Poll poll = polls.get(pollId);
        VoteOption option = voteOptions.get(voteOptionId);

        if (user == null || poll == null || option == null) {
            throw new IllegalArgumentException("Invalid user, poll, or vote option");
        }

        // Check if the poll is private and restrict access if not invited
        if (!poll.isPublic() && !poll.getInvitedUsers().contains(user)) {
            throw new IllegalStateException("User is not invited to vote in this private poll.");
        }

        // Check if the vote falls within the allowed timeframe
        Instant now = Instant.now();
        if (now.isBefore(poll.getPublishedAt()) || now.isAfter(poll.getValidUntil())) {
            throw new IllegalStateException("Voting is not allowed outside the poll's valid timeframe.");
        }

        // Check if this user already voted in a private poll (if limited to one vote)
        if (!poll.isPublic() && poll.hasUserVoted(user)) {
            throw new IllegalStateException("User is only allowed to vote once in this private poll.");
        }

        // Create a new vote
        Vote vote = new Vote(UUID.randomUUID().toString(), now, user, poll, option);
        votes.put(vote.getId(), vote);
        

        return vote;
    }


    // Get all users
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Get all polls
    public Collection<Poll> getAllPolls() {
        return polls.values();
    }

    // Get all vote options
    public Collection<VoteOption> getAllVoteOptions() {
        return voteOptions.values();
    }

    // Get all votes
    public Collection<Vote> getAllVotes() {
        return votes.values();
    }

    public List<VoteOption> getVoteOptions(String pollId) {
        Poll poll = polls.get(pollId);
        if (poll == null) {
            throw new IllegalArgumentException("Poll not found");
        }
        return poll.getVoteOptions();
    }

}
