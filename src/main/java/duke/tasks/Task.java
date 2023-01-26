package duke.tasks;

/**
 * The {@code Task} class that other {@code *Task} classes inherit from.
 */
public class Task {

    private final String name;
    private boolean isDone;

    /**
     * Instantiates a new {@code Task} object.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Converts the entire task to a string. This string is to be stored on the disk.
     *
     * @return A string representing the entire task.
     */
    public String toDukeFileString() {
        String isDoneString = this.isDone ? "1" : "0";
        return isDoneString + "|" + this.name;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "x" : " ";
        return "[" + checkMark + "] " + this.name;
    }
}
