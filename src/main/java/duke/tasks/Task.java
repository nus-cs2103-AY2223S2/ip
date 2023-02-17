package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Task object that has the name and completion status.
 */
public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Constructor for the task object,
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Checks completion status of the task and returns the
     * relevant string to show the status.
     *
     * @return The string showing its completion status.
     */
    public String marking() {
        if (this.isCompleted) {
            return ("[X] ");
        } else {
            return ("[ ] ");
        }
    }

    /**
     * Marks tasks as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks task as completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Formats date of the task if present into the specified format.
     *
     * @param date Date if present in tasks
     * @return Date in the proper format
     */
    public String dateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Returns a boolean if a task occurs within the specified date.
     *
     * @param date Date task is occurring on.
     * @return If task occurs on specified date.
     */
    public boolean isWithinDate(LocalDateTime date) {
        return false;
    }

    /**
     * Returns a boolean if a word contains in the name of the task.
     *
     * @param word The specified to be searched in the task name.
     * @return If the specified word is in the task name.
     */
    public boolean doesContain(String word) {
        String[] split = this.name.split(" ");
        for (String s : split) {
            String toTest = s.toLowerCase();
            if (toTest.equals(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the task with the provided details
     *
     * @param input The details to update the task with
     */
    public void update(String input) throws DukeException {
        this.name = input;
    }

    /**
     * Returns the task in its string form to be displayed by the Ui.
     *
     * @return The task as a string.
     */
    @Override
    public String toString() {
        return marking() + this.name;
    }

    /**
     * Converts the task into a format that will be stored into
     * the data file by the storage.
     *
     * @return The task as a string to be stored into the data file.
     */
    public String toWrite() {
        return (isCompleted ? 1 : 0) + " | " + this.name;
    }
}
