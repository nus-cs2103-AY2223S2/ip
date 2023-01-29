package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        if (from.matches("\\d{4}-\\d{2}-\\d{2}")){
            LocalDate date = LocalDate.parse(from);
            this.from = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        this.to = to;
        if (to.matches("\\d{4}-\\d{2}-\\d{2}")){
            LocalDate date = LocalDate.parse(to);
            this.to = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("E | %s | %s | %s | %s", mark, this.getDescription(), this.from, this.to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.from + " to:" + this.to + ")";
    }
}
