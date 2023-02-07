package task;

import java.time.LocalDate;
import java.time.Month;

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
        Month startMonth = this.start.getMonth();
        int startDate = this.start.getDayOfMonth();
        int startYear = this.start.getYear();

        Month endMonth = this.end.getMonth();
        int endDate = this.end.getDayOfMonth();
        int endYear = this.end.getYear();
        return "[E]" + super.toString() +
                " (from: " + startMonth + " " +startDate + ", " + startYear +
                " to: " +endMonth + " " + endDate + ", " + endYear + ")";
    }
}