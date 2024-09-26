import os

# Define the directory where the Java files will be created
directory = "PollApp"

# Create directory if it doesn't exist
os.makedirs(directory, exist_ok=True)

# Java classes to be created
java_files = {
    "User.java": """public class User {
    private String username;
    private String email;

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
}
""",

    "Poll.java": """import java.time.Instant;

public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    public Poll() {}

    public Poll(String question, Instant publishedAt, Instant validUntil) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
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
}
""",

    "VoteOption.java": """public class VoteOption {
    private String caption;
    private int presentationOrder;

    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }
}
""",

    "Vote.java": """import java.time.Instant;

public class Vote {
    private Instant publishedAt;

    public Vote() {}

    public Vote(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
""",

    "PollManager.java": """import org.springframework.stereotype.Component;

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
""",

    "UserController.java": """import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final PollManager pollManager;

    @Autowired
    public UserController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = pollManager.createUser(user.getUsername(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(pollManager.getAllUsers());
    }
}
""",

    "PollController.java": """import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Poll createdPoll = pollManager.createPoll(poll.getQuestion(), poll.getPublishedAt(), poll.getValidUntil());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoll);
    }

    @GetMapping
    public ResponseEntity<Collection<Poll>> getAllPolls() {
        return ResponseEntity.ok(pollManager.getAllPolls());
    }
}
"""
}

# Create Java files
for file_name, file_content in java_files.items():
    with open(os.path.join(directory, file_name), 'w') as java_file:
        java_file.write(file_content)

print(f"Java files generated in the '{directory}' directory.")
