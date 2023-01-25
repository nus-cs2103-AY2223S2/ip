package duke.taskers;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * unmarks the tast
     */
    public void markUndone() {
        this.isDone = false;
    }

    public String stringFormatForFile() {
        return ((this.isDone ? "1" : "0") + " / " + this.description.trim()).trim();
    }

    /**
     * format for the task string
     * @return the string representing the task
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + this.description;
    }

}
