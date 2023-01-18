package duke.tasks;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    private LocalDateTime end;

    public Deadlines (String name, String end) {
        super(name);
        this.end = LocalDateTime.parse(end);
    }

    @Override
    public boolean isWithinDate(LocalDateTime date) {
        return date.isBefore(end);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dateFormat(this.end) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + this.end + "\n";
    }
}
