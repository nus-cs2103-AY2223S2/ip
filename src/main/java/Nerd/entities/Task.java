package Nerd.entities;

/**
 * Represents the Duke.entities.Task of the Chat bot.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a Task Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        assert !this.description.equals("") : "description should not be empty!";
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Process the current Task object to be saved into a text file.
     *
     * @return A String representing the Task to be saved.
     */
    public abstract String toSave();


    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Process the current Task object to be displayed.
     *
     * @return A String representing the Task to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
