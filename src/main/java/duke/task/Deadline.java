package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String name, String start) {
        super(name);
        this.deadline = LocalDate.parse(start);
    }

    @Override
    public String getFileDesc() {
        return this.isDone
        ? "D|1|" + this.name + "|" + convertFileDate(this.deadline)
        : "D|0|" + this.name + "|" + convertFileDate(this.deadline);
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[D][X] " + this.name + " (by: " + getDate(this.deadline) + ")"
                : "[D][ ] " + this.name + " (by: " + getDate(this.deadline) + ")";
    }
}