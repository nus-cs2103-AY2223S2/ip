package connor.task;

import java.time.LocalDateTime;

/**
 * Task object that is not to be instantiated.
 */
public abstract class Task implements Comparable<Task> {

    /** String that represents the name of the task. */
    protected String taskName;
    /** Boolean that is true if a task is marked. */
    protected boolean isDone;

    /**
     * Constructor for Task that assumes that a task is not marked.
     *
     * @param taskName name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for Task that marks a task.
     * Meant for reading from memory.
     *
     * @param taskName name of the task.
     * @param isDone indicates if the task is done.
     */
    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns String representing if a task is marked.
     *
     * @return String [X] if marked.
     */
    public String getMark() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * Sets isDone to be true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets isDone to be false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the taskName of this instance.
     *
     * @return String of the taskName.
     */
    public String getTaskName() {
        assert (!this.taskName.equals(""));
        return this.taskName;
    }

    /**
     * Returns a String by converting LocalDateTime to print.
     *
     * @param input LocalDateTime representation of a dateTime.
     * @return String from LocalDateTime.
     */
    public String formatDateTime(LocalDateTime input) {
        String month = input.getMonth().toString().substring(0, 3);
        int day = input.getDayOfMonth();
        int year = input.getYear();
        String hour = String.format("%02d", input.getHour());
        String minute = String.format("%02d", input.getMinute());
        return month + " " + day + " " + year + " " + hour + minute;
    }

    /**
     * Returns a String in a format that is meant to be stored in the memory.
     *
     * @return String that represents the Task instance in the memory.
     */
    public String dataFormat() {
        assert (!this.taskName.equals(""));
        return this.isDone + "|" + this.taskName;
    }

    public abstract int compareTo(Task task);

    /**
     * Returns a String which is a concatenation of if the task is done and the taskName.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return getMark() + " " + this.taskName;
    }
}
