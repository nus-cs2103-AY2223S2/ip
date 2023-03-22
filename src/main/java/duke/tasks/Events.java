package duke.tasks;

import java.time.LocalDateTime;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.FormatException;



/**
 * A specific kind of task with a time-window.
 */
public class Events extends Task {
    public static final List<String> KEYWORDS = List.<String>of("from", "to");
    protected LocalDateTime start;


    protected LocalDateTime end;

    public Events(String description, String start, String end) throws DukeException {
        this(false, description, start, end);
    }

    public Events(boolean isDone, String description, String start, String end) throws DukeException {
        super(isDone, description);
        try {
            this.start = LocalDateTime.parse(start, Task.FORMATTER);
            this.end = LocalDateTime.parse(end, Task.FORMATTER);
        } catch (Exception err) {
            throw new FormatException("yyyy-MM-dd HH:mm", err.getMessage());
        }
    }
    public Events(List<String> queries) throws DukeException {
        this(queries.get(0), queries.get(1), queries.get(2));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start.format(Task.PRINT_FORMATTER),
                this.end.format(Task.PRINT_FORMATTER));
    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "E" + divider + isMarked + divider + this.description + divider
                + this.start.format(Task.FORMATTER) + divider + this.end.format(Task.FORMATTER);
    }

    public List<String> getKeywords() {
        return this.KEYWORDS;
    }
}
