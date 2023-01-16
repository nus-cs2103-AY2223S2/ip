package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeadlineTask)) {
            System.out.println("not same instance");
            return false;
        }
        DeadlineTask ddlObj = (DeadlineTask) obj;

        return Objects.equals(this.getInformation(), ddlObj.getInformation())
                && this.deadline.isEqual(ddlObj.deadline);
    }
}