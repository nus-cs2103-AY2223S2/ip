package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task entered by the user.
 */
abstract class Task {
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
     * Marks task if done if boolean is True otherwise False.
     *
     * @param isDone Description state of task.
     */
    void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

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

    DateTimeFormatter getFormatter() {
        return formatter;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', getTaskDescription());
    }
}
