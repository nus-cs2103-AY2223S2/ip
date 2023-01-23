package kude.models;

import java.time.LocalDateTime;

public class Deadline extends Item {
    private final LocalDateTime deadline;

    public Deadline(String content, LocalDateTime deadline) {
        super(content);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
