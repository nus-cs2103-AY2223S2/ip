package duke.tasks;

import duke.exceptions.DukeExceptions;

/**
 * Parent class of all types of tasks, contains a description of the task,
 * the tag for the type of task
 * as well as whether it is done.
 */
public class Task {
<<<<<<< HEAD
    String description;
    String tag = " ";
    boolean isDone = false;

    /**
     * formats the description.
     * @param input Input string to be formatted.
     * @throws DukeExceptions if input string is invalid.
     */
    public void formatDescription(String input) throws DukeExceptions {
        this.description = input;
    }

    /**
     * Mark the task to be done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task to be undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     * @return True if task is done and false otherwise.
     */
=======
    protected String description;
    protected String tag = " ";
    protected boolean isDone = false;

    public void formatDescription(String input) throws DukeExceptions {
        this.description = input;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

>>>>>>> branch-A-CodingStandard
    public boolean isMarked() { return this.isDone;}

    /**
     * Return the tag representing the type of task.
     * @return The tag
     */
    public String getTag() {return this.tag;}

    /**
     * Return the description of the task.
     * @return The description.
     */
    public String getDescription() {return this.description;}

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + this.tag + "]" + "[" + mark + "] " + this.description;
    }
}
