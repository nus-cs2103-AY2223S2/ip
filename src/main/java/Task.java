/**
 * The Task class.
 * Stores whatever tasks entered by the user and display them back to the user
 * when requested.
 */
public class Task {
    private final String nameOfTask;
    private final boolean isDone;

    /**
     * The constructor for Task.
     * @param nameOfTask The name of task.
     */
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.isDone = false;
    }

    /**
     * A function to get the name of Task.
     * @return The name of Task.
     */
    public String getNameOfTask() {
        return nameOfTask;
    }

    /**
     * Determines if a task is done or not.
     * @return true or false, whether a task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Determines the status of a task.
     * @return The status of the task.
     */
    public String getStatus() {
        return "[" + (isDone ? "X" : " "); + "] " + this.nameOfTask;
    }

    /**
     * A function to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
