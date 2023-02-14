package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + DIVIDER + dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + dueDate + ")";
    }
}
