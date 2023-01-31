package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task entered by the user.
 */
abstract class Task {
    String taskDescription;
    Boolean isDone = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

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
     * Gets the task description.
     *
     * @return Description of task.
     */
    String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', getTaskDescription());
    }
}
