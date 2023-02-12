package duke.task;

import duke.DukeException;
import duke.enums.Views;

/**
 * Abstract class for the Task. Every todo / deadline / event item is here
 */
public abstract class Task implements Comparable<Task> {
    private String title;
    private boolean isDone = false;

    /**
     * Creates a task, throws error if its empty string
     *
     * @param title of the Task that that is being created
     * @throws DukeException
     */
    Task(String title) throws DukeException {
        this(title, false);
    }

    /**
     * Creates a task, with isDone field. Mostly used by Storage
     *
     * @param title  of the Task that that is being created
     * @param isDone status of the Task
     * @throws DukeException
     */
    Task(String title, boolean isDone) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING);
        }
        assert title.trim().length() > 0 : Views.EMPTY_ERR_STRING.str();
        this.title = title.trim();
        this.isDone = isDone;
    }

    /**
     * Mark the task as done
     *
     * @throws DukeException
     */
    public void markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException(Views.MARKED_ERR_STRING);
        }
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     *
     * @throws DukeException
     */
    public void markAsUndone() throws DukeException {
        if (!this.isDone) {
            throw new DukeException(Views.UNMARKED_ERR_STRING);
        }
        this.isDone = false;
    }

    /**
     * Getter for title of Task
     *
     * @return String of the Task title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Method for formatting the Task to store in a txt file
     *
     * @return String of the Task
     */
    public String toExport() {
        return this.toString();
    }

    @Override
    public int compareTo(Task incoming) {
        return this.title.compareTo(incoming.title);
    }

    /**
     * Get a String representation to display to user of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        String returnString = "";
        if (isDone) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }
}
