package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String fullDescription;

    /**
     * Constructor to make a task that the user has specified and wants to be placed on the tasklist.
     *
     * @param description     the type of task.
     * @param fullDescription the entire string of user input for the task.
     */
    public Task(String description, String fullDescription) {
        this.description = description;
        this.isDone = false;
        this.fullDescription = fullDescription;
    }

    /**
     * Returns a string to see if the task is marked or unmarked.
     *
     * @return "X" if the task has been marked or empty otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Updates the isDone variable to true as the task has been done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Updates the isDone variable to false as the task has been unmarked.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the entire string of the user input.
     *
     * @return user input.
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Return true if a particular task is marked and false if task is unmarked.
     *
     * @return a boolean to see if a task has been completed.
     */
    public boolean isDoneStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}