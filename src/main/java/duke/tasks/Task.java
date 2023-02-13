package duke.tasks;
import java.io.Serializable;

/**
 * The Task object that implements Serializable.
 */
public class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The Event description.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Check if the task's description contains the given keyword.
     *
     * @param keyword The keyword argument.
     * @return A boolean value.
     */
    public boolean contains(String keyword) {
        if (description.length() >= keyword.length()) {
            return description.contains(keyword);
        } else {
            return false;
        }
    }

    /**
     * Method to mark the task as done.
     */
    public void mark() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    /**
     * Method to unmark the task as not done.
     */
    public void unmark() {
        if (this.isDone) {
            this.isDone = false;
        }
    }

    public void update(String newDescription) {
        this.description = newDescription;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format(" [ X ]  " + this.description);
        } else {
            return String.format(" [   ]  " + this.description);
        }
    }
}
