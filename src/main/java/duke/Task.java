package duke;

/**
 * Task which has 3 variations: ToDo, Deadline and Event.
 * Can be Marked to be completed or not completed.
 */
public class Task {
    private final String description;
    private Boolean isDone;

    /**
     * Creates a Task with a description, which consists of Todos, Deadlines and Events
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public String toFileString() {
        return (isDone ? "1 | " : "0 | ") + this.getDescription();
    }
}
