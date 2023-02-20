package cluck.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    public Deadline(boolean isMarked, String description, String dueDate) {
        super(isMarked, description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    public String makeSaveFormat() {
        return String.format("D|%1$s|%2$s\n", (this.isMarked ? "1" : "0"), this.description
                + "|" + this.dueDate.format(formatter));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "by: "
                + this.dueDate.format(formatter);
    }
}
