package sebastian.task;

import sebastian.time.EndTime;

import java.time.LocalDate;

/**
 * Class representing a deadline
 */
public class Deadline extends Task {

    private EndTime endTime;
    private static final String taskType = "D";

    public Deadline(int isCompleted, String taskDescription, EndTime endTime) {
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

    @Override
    public String toString() {
        return "["+ taskType +"]" + super.toString() + " " + this.endTime.toString();
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
