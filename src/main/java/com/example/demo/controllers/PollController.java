import org.springframework.beans.factory.annotation.Autowired;
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
