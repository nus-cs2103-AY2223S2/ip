package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends DukeTask {
    private final LocalDate deadline;

    public DeadlineTask(String info, LocalDate deadline) {
        super(info, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String storageString() {
        String status;
        if (this.getStatus()) {
            status = "[X] | ";
        } else {
            status = "[ ] | ";
        }
        return "[D] | " + status + this.getInformation() + " | " + this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}