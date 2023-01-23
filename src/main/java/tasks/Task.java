package tasks;

/**
 * General purpose Task class.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs Task instance.
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }
    // gets name of task
    public String getName() {
        return this.name;
    }
    // gets status of task, mark done with 'X'
    public String getStatus() {
        return (isDone ? "X" : " ");
    }
    // marks task as done
    public void markDone() {
        this.isDone = true;
    }
    // marks task as NOT done
    public void markNotDone() {
        this.isDone = false;
    }
    // properly formats Tasks.Task as String
    @Override
    public String toString() {
        return getName();
    }
    // properly formats Tasks.Task as String for saving progress
    public String toSaveString() {
        return getName().strip();
    }
}
