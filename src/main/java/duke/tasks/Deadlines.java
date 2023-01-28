package duke.tasks;

import java.time.LocalDateTime;
import java.util.List;

public class Deadlines extends Task {

    protected LocalDateTime deadline;
    private static final List<String> keywords = List.<String>of("by");
    public Deadlines(String description, String deadline) {
        this(false, description, deadline);
    }

    public Deadlines(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = LocalDateTime.parse(deadline, FORMATTER);
    }

    public Deadlines(List<String> queries) {
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
