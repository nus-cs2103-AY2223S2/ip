package botanic.task;

import java.time.LocalDate;

/**
 * Encapsulates the related fields and behavior of a task.
 */
public abstract class Task {
    //name of the task.
    private String name;

    //track whether the class is marked as done.
    private boolean isDone = false;

    /**
     * Instantiates Task with one argument given.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Instantiates a task with two arguments given.
     *
     * @param name The name of the task.
     * @param isDone Status of the task.
     */
    public Task(String name, boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    /**
     * Sets isDone to given boolean value.
     *
     * @param isDone Boolean value to set isDone to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representing the 'isDone' status.
     *
     * @return "X" if isDone is true, " " otherwise.
     */
    public String getIsDone() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Returns the string representation of the 'isDone' status and the name of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String status = getIsDone();
        return (String.format("[%s] %s", status, name));
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    public String formatForStorage() {
        String status = isDone ? "1" : "0";
        assert status == "1" || status == "0" : "Status not set properly.";
        return (String.format("%s | %s", status, name));
    }

    /**
     * Searches for given keyword string in the name of this task.
     *
     * @param keyword The given keyword string.
     * @return True if name contains keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        String formatName = " " + name.toUpperCase() + " ";
        return formatName.contains(keyword.toUpperCase());
    }

    /**
     * Searches for given date in the task description.
     *
     * @param dateToFind The given date to find.
     * @return False as Task does not have a date field.
     */
    public boolean hasDate(LocalDate dateToFind) {
        return false;
    }
}
