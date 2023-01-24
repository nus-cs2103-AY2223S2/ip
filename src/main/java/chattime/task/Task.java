package chattime.task;

import java.time.LocalDate;

/**
 * Represents task objects created by user's command.
 * Extended by Todo, Deadline and Event classes.
 */
public class Task {

    private String description;
    private boolean isDone;
    private static int count = 0;

    /**
     * Creates Task object with corresponding description.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    /**
     * Returns the symbol relevant to current task's done status.
     *
     * @return Symbol depends on done status of task.
     */
    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    /**
     * Returns current task's done status.
     *
     * @return Done status of task.
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Sets done status of current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets done status of current task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Gets total available tasks number.
     *
     * @return Total number of undeleted task.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Reduces the total task number once a task is deleted.
     */
    public void removeTask() {
        count--;
    }

    /**
     * Returns conclusion message of current total number of tasks.
     *
     * @return Conclusion message indicates total number of tasks.
     */
    public static String totalTask() {
        return "I've ound you " + count + " task(s) in the list.";
    }

    /**
     * Generates a data string of task to be stored in storage file.
     *
     * @return Data string of task.
     */
    public String toDataString() {
        return String.format(" @ %d @ %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return false as default.
     */
    public boolean onDate(LocalDate time) {
        return false;
    }

    /**
     * Returns current data of task.
     *
     * @return Current situation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
