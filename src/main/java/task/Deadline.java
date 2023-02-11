package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    @Override
    public String getSaveFormat() {
        return "D" + super.getSaveFormat() + " | " + dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + dueDate + ")";
    }
}