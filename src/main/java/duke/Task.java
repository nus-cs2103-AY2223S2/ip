package duke;

/**
 * Task which has 3 variations: ToDo, Deadline and Event.
 * Can be Marked to be completed or not completed.
 */
public class Task {
    private final String description;
    private Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public String toFileString() {
        return (done ? "1 | " : "0 | ") + this.getDescription();
    }
}
