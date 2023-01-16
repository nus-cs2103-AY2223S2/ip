package task;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task, false);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.formattedStatus(), super.task,
                super.formattedDate(this.deadline));
    }
}
