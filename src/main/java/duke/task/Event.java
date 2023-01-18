package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private String from;
    private String to;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        try {
            this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public Event(String title, String from, String to, boolean done) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        this.done = done;
        try {
            this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.from + "-" + this.to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) +
                                " to: " + this.toDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}
