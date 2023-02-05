package sebastian.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import sebastian.time.EndTime;



/**
 * Class representing a deadline
 */
public class Deadline extends Task {

    private static final String taskType = "D";

    private EndTime endTime;

    /**
     * Constructor
     * @param isCompleted whether the deadline is completed
     * @param taskDescription description of the deadline
     * @param endTime the time the deadline is due
     */
    public Deadline(boolean isCompleted, String taskDescription, EndTime endTime) {
        super(taskDescription, isCompleted);
        this.endTime = endTime;
    }

    /**
     * Checked if the deadline is on the same day as the provided date
     * @param ld a date
     * @return whether the deadline is on the same day as the provided date
     */
    public boolean isOnSameDay(LocalDate ld) {
        return endTime.isSameDay(ld);
    }

    /**
     * Update details of a deadline
     * @param description new description
     * @param endTime new end time
     */
    public void update(String description, LocalDateTime endTime) {
        if (description != null) {
            super.update(description);
        }
        if (endTime != null) {
            this.endTime.updateEndTime(endTime);
        }
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " " + this.endTime.toString();
    }

    /**
     * Format the deadline into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave() + "<>" + endTime.formatForSave();
    }
}
