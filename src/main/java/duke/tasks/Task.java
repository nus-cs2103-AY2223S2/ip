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
        isDone = false;
    }

    /**
     * Copy constructor for the {@code Task} class.
     *
     * @param other The {@code Task} to be deep copied.
     */
    public Task(Task other) {
        name = other.name;
        isDone = other.isDone;
    }

    public Task deepClone() {
        return new Task(this);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkDone() {
        isDone = false;
    }

    public boolean matchName(String name) {
        return this.name.contains(name);
    }

    /**
     * Converts the entire task to a string. This string is to be stored on the disk.
     *
     * @return A string representing the entire task.
     */
    public String toDukeFileString() {
        String isDoneString = isDone ? "1" : "0";
        return isDoneString + "|" + name;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "x" : " ";
        return "[" + checkMark + "] " + name;
    }
}
