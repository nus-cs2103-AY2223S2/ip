package duke.task;

/**
 * The class Task encapsulates a task.
 * All objects of class Task have a description on what the task is about.
 * Each task will also be indicated as not done without a 'X', or done with a 'X'.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description,
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done or not done.
     * @return A 'X' if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the status of the task to done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Checks if the string given is in the task description.
     * @param string The string given.
     * @return True if the string given is in the task description, and false otherwise.
     */
    public boolean isInDescription(String string) {
        return this.description.contains(string);
    }

    /**
     * Changes the status of the task to undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this);
    }

    /**
     * The string representation of the Task object.
     * @return The name of this task and if the task is done or not.
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    //...
}
