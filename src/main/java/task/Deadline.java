package task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDateTime;

/**
 * Deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for "Deadline" type task.
     * @param description The task description.
     * @param by The deadline of the task.
     * @throws DukeException If there is no description or deadline indicated.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getSavedFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + Parser.dePrettifyDateTime(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + Parser.prettifyDateTime(this.by) + ")";
    }
}
