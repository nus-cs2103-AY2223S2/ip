package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor to create a new event
     * @param task Event task
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Convert the Event task into data from for storage into file.
     * @return String representation of the data.
     */
    public String toData() {
        String status = this.completed ? "1" : "0";
        return "E | " + status + " |" + this.task + "| " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        String formattedStart = from.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        String formattedEnd = to.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        return "[E][" + statusIcon + "] " + this.task + "(from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
