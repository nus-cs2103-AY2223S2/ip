package duke.task;

import java.util.Comparator;

/**
 * The class Task encapsulates a task.
 * All objects of class Task have a description on what the task is about.
 * Each task will also be indicated as not done without a 'X', or done with a 'X'.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done or not done.
     *
     * @return duke.task.A 'X' if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the status of the task to done.
     */
    public String markAsDone() {
        this.isDone = true;
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append("Nice! I've marked this task as done:\n");
        chunkOfText.append(this.toString());
        chunkOfText.append('\n');
        return chunkOfText.toString();
    }

    /**
     * Checks if the string given is in the task description.
     *
     * @param string The string given.
     * @return True if the string given is in the task description, and false otherwise.
     */
    public boolean isInDescription(String string) {
        return this.description.contains(string);
    }

    /**
     * Changes the status of the task to undone.
     */
    public String markAsUnDone() {
        this.isDone = false;
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append("Ok, I've marked this task as not done yet:\n");
        chunkOfText.append(this.toString());
        chunkOfText.append('\n');
        return chunkOfText.toString();
    }

    /**
     * Returns the string representation of the Task object.
     *
     * @return The name of this task and if the task is done or not.
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

}
