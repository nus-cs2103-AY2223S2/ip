package duke.task;

/**
 * The Task class.
 * Stores whatever tasks entered by the user and display them back to the user
 * when requested.
 * Acts as a superclass for all Task types.
 */
public class Task {
    protected String nameOfTask;
    protected boolean isDone;
    protected char typeOfTask;

    /**
     * The constructor for Task.
     *
     * @param nameOfTask The name of task.
     *
     */
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.isDone = false;
    }

    /**
     * Determines the status of a task.
     * @return The status of the task.
     */
    public String getStatus() {
        return isDone
                ? "X"
                : " ";
    }

    public String getNameOfTask() {
        return this.nameOfTask = nameOfTask;
    }

    /**
     * A function to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A function to unmark a task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * A function to label status of task as an int.
     * @return 1 if isDone is true, 0 otherwise.
     */
    public int isDoneToInt() {
        return isDone
                ? 1
                : 0;
    }
    
    @Override
    public String toString() {
        return "[" + getStatus() + "]" + this.nameOfTask;
    }
}
