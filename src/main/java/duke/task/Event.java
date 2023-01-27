package duke.task;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final Pattern p = Pattern.compile("(.+) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2})");
    protected LocalDate to;
    protected LocalDate from;

    public Event(String description, String to, String from) {
        super(description);
        this.to = LocalDate.parse(to);
        this.from = LocalDate.parse(from);
        classIcon = "E";
    }

    @Override
    public String toString() {
        return String.format(
                "%s From: %s To: %s", super.toString(),
                to.format(Task.outputFormat),
                from.format(Task.outputFormat));
    }

    @Override
    public String toLog() {
        return String.format("%s /from %s /to %s", super.toString(), to.format(Task.inputFormat), from.format(Task.inputFormat));
    }
}