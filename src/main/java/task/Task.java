package task;

/**
 * Class to represent a task created by Duke.
 */
public class Task {
    private String name;
    private Boolean isCompleted;

    /**
     * Constructor for a Task.
     *
     * @param name Title/name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Constructor for a Task when loaded in from hard drive.
     *
     * @param name Title/name of Task.
     * @param isDone True if Task has been marked as done, false otherwise.
     */
    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isCompleted = isDone;
    }

    /**
     * To mark a Task as done.
     */
    public void check() {
        this.isCompleted = true;
    }

    /**
     * To unmark a done Task.
     */
    public void unCheck() {
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
