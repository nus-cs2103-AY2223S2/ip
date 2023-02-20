package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task object that can be either deadlines, todos, or events.
     * @param description the contents of the tasks.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Constructs an empty and fake task object.
     */
    public Task() {
        this.description = "fake task";
        this.isDone = false;
    }

    /**
     * Signifies whether this task is null and fake.
     * @return An indicator of whether this task is null.
     */
    public boolean isNull() {
        return this.description.equals("fake task");
    }

    /**
     * Gets the string representation of task status.
     * @return A string of task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}


