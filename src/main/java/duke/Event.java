package duke;

import java.time.LocalDate;
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String instruction, String start, String end) {
        super(instruction.substring(5));
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