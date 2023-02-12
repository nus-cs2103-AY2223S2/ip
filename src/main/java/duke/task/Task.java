package duke.task;

import java.time.LocalDate;

/**
 * Abstract class contains variables and methods related to Tasks.
 */
public abstract class Task {
    protected boolean isCompleted;
    protected String taskName;

    /**
     * Creates an instance of a Task.
     * @param taskname String name of task.
     */
    public Task(String taskname) {
        this.taskName = taskname;
        this.isCompleted = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.isCompleted = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks whether task falls on the given date.
     * @param date Date to be checked against.
     * @return true if task falls on the date and false otherwise.
     */
    public abstract boolean isTaskInSchedule(LocalDate date);

    /**
     * Marks task as done or undone depending on status.
     * @param status
     */
    public void setCompleted(boolean status) {
        this.isCompleted = status;
    }

    /**
     * Returns Task as a String formatted for saving into a file.
     * @return String formatted for fie input.
     */
    public String toFile() {
        if (this.isCompleted) {
            return String.format("1 | %s", this.taskName);
        } else {
            return String.format("0 | %s", this.taskName);
        }
    }

    /**
     * Returns Task as a String.
     * @return Task formatted as a String.
     */
    @Override
    public String toString() {
        String s;
        if (this.isCompleted) {
            s = "[X] " + this.taskName;
        } else {
            s = "[ ] " + this.taskName;
        }
        return s;
    }
}
