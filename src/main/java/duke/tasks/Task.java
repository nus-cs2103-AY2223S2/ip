package duke.tasks;
import java.io.Serializable;

/**
 * It creates a class called Task that has two properties: description and isDone status.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    /**
     * If the task is done, return an X, otherwise return a space
     * @return The method is returning a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * marks a task as done by changing isDone variable to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This function sets the value of the isDone variable to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
