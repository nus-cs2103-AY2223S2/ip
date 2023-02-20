package duke.tasks;

/**
 * The {@code TodoTask} class for tasks that have neither a deadline nor start and end times.
 */
public class TodoTask extends Task {

    /**
     * Instantiates a new {@code TodoTask} object.
     *
     * @param name The name of the task.
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * Copy constructor for the {@code TodoTask} class.
     *
     * @param other The {@code TodoTask} to be deep copied.
     */
    public TodoTask(TodoTask other) {
        super(other);
    }

    public TodoTask deepClone() {
        return new TodoTask(this);
    }

    public String toDukeFileString() {
        return "T|" + super.toDukeFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
