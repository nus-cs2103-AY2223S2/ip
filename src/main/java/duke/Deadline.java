package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Models an deadline which is a task.
 */
public class Deadline extends Task {

    /** String used to represent the deadline of task. */
    protected String taskEndTime;

    /** String used to assign the name of the task. */
    protected String taskDesc;

    /** Date object used to represent the deadline of task. */
    private LocalDate byDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param taskDesc The name of the task.
     * @param taskEndTime The deadline of the task.
     */
    public Deadline(String taskDesc, String taskEndTime) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.taskEndTime = taskEndTime;
        byDate = LocalDate.parse(taskEndTime);
    }

    /**
     * Overloaded constructor for the Deadline class.
     *
     * @param taskDesc The name of the task.
     * @param taskEndTime The deadline of the task.
     * @param taskStatus The status of the task.
     */
    public Deadline(String taskDesc, String taskEndTime, boolean taskStatus) {
        super(taskDesc, taskStatus);
        this.taskDesc = taskDesc;
        this.taskEndTime = taskEndTime;
        byDate = LocalDate.parse(taskEndTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String asCsv() {
        if (super.taskStatus) {
            return "D,1" + taskDesc + "," + taskEndTime;
        } else {
            return "D,0" + taskDesc + "," + taskEndTime;
        }
    }
}
