package duke;

/**
 * Task class to store individual tasks that the user enters
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status of task, whether it is done or not
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Retrieves status icon of task based on whether it is done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Toggles status of task between done and not done
     */
    public void toggleMarked() {
        this.isDone = !this.isDone;
    }

    /**
     * Converts Task to formatted String to be saved in
     */
    public String toSavedString() {
        String savedString = this.isDone ? "1 | " + this.description : "0 | " + this.description;
        return savedString;
    }

    /**
     * Converts Task to formatted String to be displayed
     */
    @Override
    public String toString() {
        String outputString = this.getStatusIcon() + " " + this.description;
        return outputString;
    }
}
