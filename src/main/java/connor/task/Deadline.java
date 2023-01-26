package connor.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {

    /** LocalDateTime representation of the deadline of the this task. */
    private LocalDateTime deadline;

    /** data format representation of this instance to be stored in memory. */
    private String dataFormat;

    /**
     * Constructor to instantiate a new Deadline using only taskName and DateTime.
     *
     * @param taskName name of the task.
     * @param DateTime the due date of this task.
     */
    public Deadline(String taskName, String DateTime) {
        super(taskName);
        this.taskName = taskName;
        try {
            this.deadline = parseDateTime(DateTime);
            this.dataFormat = dateTimeFormat(DateTime);
        } catch (DateTimeException e) {
            System.out.println("        " + e.getMessage());
        }
    }

    /**
     * Constructor to instantiate a Deadline from the memory.
     *
     * @param taskName name of the task.
     * @param isDone indicates if task is done.
     * @param dataFormat the dateformat of this task.
     */
    public Deadline(String taskName, Boolean isDone, String dataFormat) {
        super(taskName, isDone);
        this.taskName = taskName;
        this.deadline = LocalDateTime.parse(dataFormat);
        this.dataFormat = dataFormat;
    }

    /**
     * Returns a String in a format that is meant to be stored in the memory with additional deadline information.
     *
     * @return String that represents the Task instance in the memory.
     */
    @Override
    public String dataFormat() {
        return "D|" + super.dataFormat() + "|" + this.dataFormat;
    }

    /**
     * Returns a String which is a concatenation of task type, if the task is done, taskName and deadline.
     *
     * @return String representation of the task to be printed.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.formatDateTime(this.deadline)
                + ")";
    }
}
