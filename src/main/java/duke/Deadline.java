package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructor for deadline tasks
     * @param name Deadline task description
     * @param deadline Deadline task deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline.trim());
    }

    /**
     * Formats deadline task to saving format
     * @return Formatted task string
     */
    @Override
    public String toSave() {
        if (super.isDone()) {
            return "D | 1 | " + super.getName() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
        } else {
            return "D | 0 | " + super.getName() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
        }
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
