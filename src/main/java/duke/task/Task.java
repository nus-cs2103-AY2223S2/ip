package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

/**
 * Represents a Task.
 * Task information are stored here.
 */
public class Task {
    protected final int taskNumber;
    protected final boolean taskStatus;
    protected final String task;
    protected final int totalNumOfTasks;

    Task(int taskNumber, boolean taskStatus, String task, int totalNumOfTasks) {
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        this.task = task;
        this.totalNumOfTasks = totalNumOfTasks;
    }

    /**
     * Prints that task is marked as done when task is done.
     *
     * @return String response that show task is marked as done.
     */
    public String markAsDone() {
        return "\t____________________________________________________________"
                + "\n\t Nice! I've marked this task as done:\n"
                + "\t  " + "[X]" + " " + this.task
                + "\n\t____________________________________________________________";
    }

    /**
     * Prints that task is marked as not done when task is unmarked.
     *
     * @return String response that show task is unmarked as undone.
     */
    public String unmarkAsUndone() {
        return "\t____________________________________________________________"
                + "\n\t OK, I've marked this task as not done yet:\n"
                + "\t  " + "[ ]" + " " + this.task
                + "\n\t____________________________________________________________";
    }

    /**
     * Prints that task is deleted.
     *
     * @return String response that show task is deleted.
     */
    public String printDelete(List<Task> allTasks) {
        int allTaskSize = allTasks.size() - 1;
        return "\t____________________________________________________________"
                + "\n\t Noted. I've removed this task:" + "\n\t   "
                + this.getTaskStatus() + " " + this.task
                + "\n\t Now you have " + allTaskSize + " tasks in the list.";
    }

    /**
     * Gets task number.
     *
     * @return Index of task on task list.
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }

    /**
     * Gets type of task.
     * Returns empty string as parent class is not a specific type of
     * task.
     *
     * @return String of task type.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Gets whether task is done.
     *
     * @return String of brackets that fills with X if task is done.
     */
    public String getTaskStatus() {
        if (!this.taskStatus) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

    /**
     * Gets task.
     *
     * @return Task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets deadline.
     * Returns null as parent class does not require deadlines.
     *
     * @return Date and time of deadline.
     */
    public LocalDateTime getDeadline() {
        return null;
    }

    /**
     * Gets event start time.
     * Returns null as parent class does not have a start time.
     *
     * @return Date and time of event start time.
     */
    public LocalDateTime getEventStartTime() {
        return null;
    }

    /**
     * Gets event start time.
     * Returns null as parent class does not have an end time.
     *
     * @return Date and time of event end time.
     */
    public LocalDateTime getEventEndTime() {
        return null;
    }

    /**
     * Gets task date.
     * Returns null as parent class does not have a date required.
     *
     * @return Date of task.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Overrides default equal method such that same type of object
     * can pass Junit test.
     *
     * @param o Object to be compared to.
     * @return Boolean of whether object has same class.
     */
    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass();
    }
}
