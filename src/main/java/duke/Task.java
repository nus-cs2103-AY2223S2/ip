package duke;

/**
 * A Task object represents a task that can be created by the user
 */

public class Task {
    protected final String description;
    protected boolean isDone = false;


    /**
     * Constructor for the Task object
     * @param description A brief description of the task
     */
    public Task (String description) {
        this.description = description;
    }

    /**
     * Method that creates a Task object by calling the constructor of the Task class
     * @param description A brief description of the task
     * @return The Task object that was created
     */
    public Task createTask(String description) {
        return new Task(description);
    }

    /**
     * Method that marks a task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Method that marks a task is incomplete
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone? "[X] " : "[ ] ") + description;
    }




}
