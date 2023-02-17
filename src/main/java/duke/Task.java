package duke;

/**
 * Class Tasks encapsulate details
 * about a user's task in stored in a duke.Duke object.
 *
 * @author hhchinh2002
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a duke.Task object with given description
     *
     * @param description The description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the current task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the current task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).getDescription().equals(this.getDescription());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
