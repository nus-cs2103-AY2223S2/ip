package duke.tasks;
import java.io.Serializable;

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
     * Method to mark the task as done.
     */
    public void mark(){
        if (!this.done) {
            this.done = true;
        }
    }

    /**
     * Method to unmark the task as not done.
     */
    public void unmark(){
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
            return String.format("[X] " + this.description);
        } else {
            return String.format("[ ] " + this.description);
        }
    }
}
