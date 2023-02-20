package windycall.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;


    public Event(String description, boolean status, LocalDate from, LocalDate to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean status, LocalDate from, LocalDate to, String tag) {
        super(description, status, tag);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }


    @Override
    public String getFileFormat() {
        return "E | " + getStatusIcon() + " | " + tag + " | " + description + " | " + from + " | " + to + "\n";
    }
    private String changeDateTimeFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getEventInterval() {
        return " (from: " + changeDateTimeFormat(from) + " to: " + changeDateTimeFormat(to) + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getEventInterval();
    }
}
