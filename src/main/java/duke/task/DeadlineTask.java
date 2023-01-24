package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A DeadlineTask that encapsulates the information and deadline date of a Deadline Task.
 */
public class DeadlineTask extends DukeTask {
    /** The deadline date of the task */
    public final LocalDate deadline;

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

    /**
     * Returns true if the given date is equal to the deadline of the task.
     *
     * @param date The date to check
     * @return true if the date is equal to the deadline, false otherwise
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        return date.isEqual(this.deadline);
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return The deadline date of the task
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of the task in a specific format, indicating whether the task is done or not,
     * the information of the task and the deadline.
     *
     * @return A string representation of the task
     */
    @Override
    public String storageString() {
        String status = this.getStatus() ? "[X] | " : "[ ] | ";
        return "[D] | " + status + this.getInformation() + " | " + this.deadline;
    }

    /**
     * Returns a string representation of the task in a specific format, indicating the task type, whether the task is
     * done or not, the information of the task and the deadline.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Compares this task object to the passed object and returns true if they are equal.
     * Two DeadlineTask objects are considered equal if their information and deadline are equal.
     *
     * @param obj The object to compare
     * @return true if the objects are equal, false otherwise
     */
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
