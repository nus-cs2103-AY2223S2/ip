package kude.models;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Representation of a task with a deadline
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Create a new Deadline
     * @param content Content of the deadline
     * @param deadline Deadline timing as a LocalDateTime
     */
    public Deadline(String content, Optional<String> place, LocalDateTime deadline) {
        super(content, place);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline for the task
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
