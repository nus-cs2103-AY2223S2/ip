package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A DeadlineTask that encapsulates the information and deadline date of a Deadline Task.
 */
public class DeadlineTask extends DukeTask {
    private final LocalDate deadline;

    /**
     * Constructor for DeadlineTask that takes in the information of the task and its Deadline.
     *
     * @param info The information of the task
     * @param deadline The deadline of the task
     */
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
    public boolean matchesDate(LocalDate date) {
        return date.isEqual(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDeadline() {
        return getDeadline();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask ddlObj = (DeadlineTask) obj;

        return Objects.equals(this.getInformation(), ddlObj.getInformation())
                && this.deadline.isEqual(ddlObj.deadline);
    }
}
