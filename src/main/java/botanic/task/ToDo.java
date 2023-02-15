package botanic.task;

/**
 * Encapsulates the related fields and behavior of a ToDo task.
 * Represents a task without any date/time attached.
 */
public class ToDo extends Task {
    /**
     * Instantiates ToDo with one argument given.
     *
     * @param name The name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Instantiates ToDo with two arguments given.
     *
     * @param name The name of the task.
     * @param isDone Status of the task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string with a [T] icon representing this task.
     *
     * @return A string representation of this ToDo task.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    @Override
    public String formatForStorage() {
        return ("T | " + super.formatForStorage());
    }
}
