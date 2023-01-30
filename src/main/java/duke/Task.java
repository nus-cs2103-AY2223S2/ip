package duke;

/**
 * Encapsulates the tasks as objects to be added to a list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Creates a string format of the task reflecting its completion status.
     * @return The string format of the task to be added to the list.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Creates a string format of the task to be output to the text file.
     * @return The string format of the task to be used in the text file.
     */
    public String sendOutputToFile() {
        return String.format("Task | %d | %s", isDone ? 1 : 0, description);
    }
}
