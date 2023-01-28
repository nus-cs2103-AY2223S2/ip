package duke.tasks;

import java.time.LocalDateTime;
import java.util.List;

public class Events extends Task {
    protected LocalDateTime start;
    private static final List<String> keywords = List.<String>of("from", "to");

    protected LocalDateTime end;

    public Events(String description, String start, String end) {

        this(false, description, start, end);
    }

    public Events(boolean isDone, String description, String start, String end) {
        super(isDone, description);

        this.start = LocalDateTime.parse(start, Task.FORMATTER);
        this.end = LocalDateTime.parse(end, Task.FORMATTER);
    }


    public Events(List<String> queries) {
        this(queries.get(0), queries.get(1), queries.get(2));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start.format(Task.FORMATTER),
                this.end.format(Task.PRINTFORMATTER));
    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "E" + divider + isMarked + divider + this.description + divider +
                this.start.format(Task.FORMATTER) + divider + this.end.format(Task.FORMATTER);
    }

    public List<String> getKeywords() {
        return this.keywords;
    }
}
