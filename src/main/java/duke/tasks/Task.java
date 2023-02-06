package duke.tasks;
import java.io.Serializable;

/**
 * The Task object that implements Serializable.
 */
public class Task implements Serializable {
    private String description;
    private boolean done;

    /**
     * Constructor for a Task object.
     *
     * @param description The Event description.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
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
        if (!this.done) {
            this.done = true;
        }
    }

    /**
     * Method to unmark the task as not done.
     */
    public void unmark() {
        if (this.done) {
            this.done = false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if (this.done) {
            return String.format(" [x] " + this.description);
        } else {
            return String.format(" [ ] " + this.description);
        }
    }
}
