package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDate.parse(start.trim());
        this.end = LocalDate.parse(end.trim());
    }

    @Override
    public String toSave() {
        if (super.isDone()) {
            return "D | 1 | " + super.getName() + " | "
                    + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | "
                    + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
        } else {
            return "D | 0 | " + super.getName() + " | "
                    + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | "
                    + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
