package peppa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. Deadlines are given by the user in the format deadline {desc} /by {dateDue} {timeDue}.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma")) + ")";
    }

    @Override
    public String toFormatString() {
        return "D | " + (super.done ? "1" : "0") + " | " + super.name + " | "
                + this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma"));
    }
}
