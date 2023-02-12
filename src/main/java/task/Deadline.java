package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task which consist of the details and due date.
 */
public class Deadline extends Task {

    /** Due date for this task */
    private LocalDate endTime;

    /**
     * Returns an instance of Deadline object mark undone.
     *
     * @param taskDetails Task details.
     * @param endTime Task due date.
     */
    public Deadline(String taskDetails, String endTime) {
        super(taskDetails);
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns an instance of Deadline object.
     *
     * @param isTaskDone Task done status.
     * @param taskDetails Task details.
     * @param taskDate Task due date.
     */
    public Deadline(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        this.endTime = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String writeToFile() {
        if (!isTaskDone) {
            return "D| |"
                    + this.taskName
                    + "|"
                    + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "D|X|"
                + this.taskName
                + "|"
                + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        if (!isTaskDone) {
            return "[D][ ] " + this.taskName
                    + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
        }
        return "[D][X] " + this.taskName
                + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }

}
