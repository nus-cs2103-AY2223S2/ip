package smartduke.task;

import smartduke.DukeException;
import smartduke.Parser;

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
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.length() == 0) {
            throw new DukeException("OOPS!!! You need to indicate a deadline for this task...");
        }
        this.by = Parser.parseDateTime(by);
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
