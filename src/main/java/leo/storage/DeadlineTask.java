package leo.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task. A <code>DeadlineTask</code> object corresponds to
 * a Task containing the String description and LocalDateTime deadline.
 */
public class DeadlineTask extends Task {

    private final LocalDateTime deadline;

    /**
     * Constructor to create a DeadlineTask object.
     *
     * @param task Description of the task.
     * @param by Deadline of the task.
     */
    public DeadlineTask(String task, LocalDateTime by) {
        super(task);
        this.deadline = by;
        setType(TaskType.DEADLINE);
    }

    /**
     * Returns type, status, description and deadline of Task.
     *
     * @return String representation of DeadlineTask.
     */
    @Override
    public String toString() {
        return typeAndStatus() + getTask() + " (by: " + getDeadline() + ")";
    }

    /**
     * Returns the String representation of Task that is to be saved in the data file.
     *
     * @return String representation of DeadlineTask.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        String strDeadline = formatter.format(this.deadline);
        return typeAndStatus() + getTask() + " | " + strDeadline + "\n";
    }

    /**
     * Returns formatted deadline obtained from LocalDateTime.
     *
     * @return String representation of the deadline.
     */
    private String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        return formatter.format(this.deadline);
    }

    public boolean sameDay(LocalDate day) {
        LocalDate deadlineDate = this.deadline.toLocalDate();
        return deadlineDate.equals(day);
    }
}
