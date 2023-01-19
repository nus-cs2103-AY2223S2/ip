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
     * Get the string with a [T] icon representing this task.
     * @return A string representation of this ToDo task.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
