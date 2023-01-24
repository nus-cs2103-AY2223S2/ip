/**
 * Encapsulation of a ToDo task,
 * a task without any date/time attached.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * @param name The name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor to instantiate a todo task.
     * @param name The name of the task.
     * @param isDone Status of the task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Get the string with a [T] icon representing this task.
     * @return A string representation of this ToDo task.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Format task to be stored in data file.
     * @return Returns a  formatted string representation of this task to be stored.
     */
    @Override
    public String format() {
        return ("T | " + super.format());
    }
}
