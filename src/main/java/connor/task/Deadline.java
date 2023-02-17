package connor.task;

import java.time.LocalDateTime;

/**
 * Deadline object that keeps track of a task to be finished by.
 */
public class Deadline extends Task {

    /** LocalDateTime representation of the deadline of this task. */
    private LocalDateTime deadline;

    /** Data format representation of this instance to be stored in memory. */
    private String dataFormat;

    /**
     * Constructor to instantiate a new Deadline using only taskName and DateTime.
     *
     * @param taskName name of the task.
     * @param deadline the due date of this task.
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
        this.dataFormat = deadline.toString();

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

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            return 1;
        } else if (task instanceof Event) {
            return -1;
        }
        Deadline newTask = (Deadline) task;
        if (this.deadline.equals(newTask.deadline)) {
            return this.taskName.compareTo(newTask.taskName);
        } else {
            return this.deadline.compareTo(newTask.deadline);
        }
    }

    /**
     * Returns a String which is a concatenation of task type, if the task is done, taskName and deadline.
     *
     * @return String representation of the task to be printed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(this.deadline) + ")";
    }
}
