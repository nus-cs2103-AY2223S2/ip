package duke.tasks;

/**
 * Stores inputs of tasks
 */
public class Task {
    private final String name;
    private boolean checkMark;

    /**
     * Initialises input to the class
     *
     * @param name name of the tasks
     */
    public Task(String name) {
        this.name = name;
        this.checkMark = false;
    }

    /**
     * Sets checkMark as true
     */
    public void toBeMarked() {
        this.checkMark = true;
    }

    /**
     * Sets checkMark as false
     */
    public void toBeUnmarked() {
        this.checkMark = false;
    }

    /**
     * Displays name, date and time of the task
     *
     * @return shows the item
     */
    @Override
    public String toString() {
        return (checkMark ? "[X] " : "[] ") + name;
    }
}
