package task;

import duke.DukeException;
import duke.IncompleteCommandDukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The base class for all tasks.
 */

public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    /**
     * The default constructor
     */
    public Task() {
        this("");
    }

    /**
     * The task string description
     *
     * @param description a string describing the task, which should include the parameters
     *                   corresponding to the task
     */
    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * Gives the string representation of date
     *
     * @param date a date object
     * @return the corresponding string representation
     */
    protected static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get the name of the task. The name is the content of the task (what to do)
     *
     * @return the string name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get whether the task is done.
     *
     * @return the status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Get the string icon of the status of the task. It is either space or X.
     *
     * @return the string icon of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Return the type of the task, which is a character,
     * such as "D" for deadline and "E" for event
     *
     * @return the type character
     */
    public String getType() {
        return type;
    }

    /**
     * Checks if the task name contains a given string
     * @param string a given string
     * @return a boolean value
     */
    public boolean containString(String string) {
        assert string.length() > 0 : "input string length should not be zero";
        return toString().contains(string);
    }

    /**
     * Returns the index of the given keyword
     * @param description the user-input description
     * @param keyword the keyword to search for
     * @return the index of the keyword
     * @throws DukeException when the keyword cannot be found
     */
    protected int getKeywordIndex(String description, String keyword) throws DukeException {
        int keywordIndex = description.indexOf(keyword);
        if (keywordIndex < 0) {
            throw new IncompleteCommandDukeException(String.format("Keyword %s is missing.", keyword));
        }
        return keywordIndex;
    }

    /**
     * Overrides the original toString method. The status and the task content
     * are reflected in the string representation.
     *
     * @return the task string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
