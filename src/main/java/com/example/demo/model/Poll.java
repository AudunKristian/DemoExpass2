package com.example.demo.model;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

public class Poll {
    private String id; // Unique identifier for the poll
    private String question;
    private boolean isPublic;
    private boolean limitVotesPerUser; // For private polls, limit votes to 1 per user
    private Instant publishedAt;
    private Instant validUntil;
    private List<User> invitedUsers;
    
    @JsonBackReference // Prevent recursion when serializing User
    private User creator; // Link to the poll creator

    @JsonManagedReference // Poll manages VoteOption serialization
    private List<VoteOption> voteOptions;

    @JsonManagedReference // Poll manages Vote serialization
    private List<Vote> votes;


    public Poll() {}

    public Poll(String id, String question, Instant publishedAt, Instant validUntil, User creator, List<VoteOption> voteOptions, List<Vote> votes, List<User> invitedUsers) {
        this.id = id;
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.creator = creator; 
        this.voteOptions = voteOptions;
        this.votes = votes;
        this.invitedUsers = invitedUsers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }   

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
    
    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    public boolean limitVotesPerUser() {
        return limitVotesPerUser;
    }

    public void setLimitVotesPerUser(boolean limitVotesPerUser) {
        this.limitVotesPerUser = limitVotesPerUser;
    }   

    public boolean hasUserVoted(User user) {
        return votes.stream().anyMatch(vote -> vote.getVoter().equals(user));
    }

    public List<User> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(List<User> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

}
