package windycall.task;

import windycall.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;


    public Event(String description, boolean status, String from, String to) {
        super(description, status);
        this.from = Parser.processDate(from);
        this.to = Parser.processDate(to);
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }


    @Override
    public String getFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to + "\n";
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
