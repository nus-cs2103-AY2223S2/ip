package brotherbot.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description + " By: " + this.deadline.format(formatter);
    }

}
