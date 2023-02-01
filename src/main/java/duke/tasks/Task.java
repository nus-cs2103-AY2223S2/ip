package duke.tasks;

/**
 * Abstract class that represents a task.
 * A task must come with a title and can be mark as done or not done.
 */
abstract public class Task {
    protected String title;
    protected boolean isDone;

    protected Task (String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Marks the Task object as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task object as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String that contains information
     * on the Task object that is used for loading
     * the task into the ToDoList on Duke startup.
     *
     * @return A String that is used for loading the task into Duke on startup.
     */
    abstract public String save();

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return status + this.title;
    }
}
