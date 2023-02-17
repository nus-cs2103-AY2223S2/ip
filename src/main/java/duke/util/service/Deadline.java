package duke.util.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.util.Task;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Construct a {@code Deadline} with the
     * deadline and action specified by the user
     * with the keyword "/BY".
     */
    public Deadline(LocalDateTime deadline, String action) {
        super("D", action);
        this.deadline = deadline;
    }

    public Deadline(LocalDateTime deadline, String action, boolean isDone) {
        super("D", action, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getAdditionalInfo() {
        return "/BY " + deadline.format(
                DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy"));
    }

    public List<LocalDateTime> getDates() {
        return List.of(this.deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline.format(
                DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + ")";
    }
}
