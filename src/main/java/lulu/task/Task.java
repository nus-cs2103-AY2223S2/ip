package lulu.task;

/**
 * Represents a task. A task has a description, and a isDone attribute to indicate whether the task has been completed.
 * All tasks are initially marked as uncompleted.
 * A task has methods to update its description and mark or unmark its completion.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean containsDescription(String s) {
        return this.description.contains(s);
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public String toMemory() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public abstract void update(String updateInformation);
}
