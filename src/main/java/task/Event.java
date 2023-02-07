package task;

import java.time.LocalDate;

/**
 * One of the three types of task a user can add.
 * Consist of 2 date elements (beginning and end).
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String instruction, String start, String end) {
        super(instruction.substring(6));
        this.start = LocalDate.parse(start.trim());
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.start.getMonth() + " " + this.start.getDayOfMonth() + ", " + this.start.getYear() +
                " to: " + this.end.getMonth() + " " + this.end.getDayOfMonth() + ", " + this.end.getYear() + ")";
    }
}