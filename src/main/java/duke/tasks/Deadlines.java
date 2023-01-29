package duke.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.FormatException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A specific kind of task with a deadline.
 */
public class Deadlines extends Task {

    protected LocalDateTime deadline;
    private static final List<String> keywords = List.<String>of("by");
    public Deadlines(String description, String deadline) throws DukeException {
        this(false, description, deadline);
    }

    public Deadlines(boolean isDone, String description, String deadline) throws DukeException {
        super(isDone, description);
        try {
            this.deadline = LocalDateTime.parse(deadline, FORMATTER);
        } catch (Exception err) {
            throw new FormatException("yyyy-MM-dd HH:mm");
        }

    }

    public Deadlines(List<String> queries) throws DukeException {
        this(queries.get(0), queries.get(1));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(PRINTFORMATTER));

    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */

    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "D" + divider + isMarked + divider + this.description + divider +
                this.deadline.format(FORMATTER);
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

}
