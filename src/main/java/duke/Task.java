package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task entered by the user.
 */
abstract class Task implements Comparable<Task> {
    private String taskDescription;
    private Boolean isDone = false;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    /**
     * Marks task as done.
     */
    void mark() {
        isDone = true;
    }

    /**
     * Unmarks task as done.
     */
    void unmark() {
        isDone = false;
    }

    /**
     * Sets task status to boolean.
     *
     * @param isDone Description state of task.
     */
    void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the task description.
     *
     * @param taskDescription of task.
     */
    void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Gets the task description.
     *
     * @return Description of task.
     */
    String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Gets the Formatter.
     *
     * @return The Formatter.
     */
    DateTimeFormatter getFormatter() {
        return formatter;
    }

    @Override
    public int compareTo(Task task) {
        return this.getTaskDescription().compareTo(task.getTaskDescription());
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', getTaskDescription());
    }
}
