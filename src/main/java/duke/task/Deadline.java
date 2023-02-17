package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String name, String start) {
        super(name);
        this.deadline = LocalDate.parse(start);
    }

    public String changeDate(LocalDate date) {
        this.deadline = date;
        return "I have changed the date:\n" +
                this.toString();
    }

    @Override
    public String getFileDesc() {
        return "D|" + super.getFileDesc() + "|" + convertFileDate(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate(this.deadline) + ")";
    }
}