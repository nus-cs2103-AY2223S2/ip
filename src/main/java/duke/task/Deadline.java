package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime doneBy;

    public Deadline(String description, LocalDateTime doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    public Deadline(String description, boolean isDone, LocalDateTime doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | by: %s", super.toString(), formatSavedDateTime(doneBy));
    }

    @Override
    public String toString() {
        return String.format("D | %s | by: %s", super.toString(), formatDateTime(doneBy));
    }
}
