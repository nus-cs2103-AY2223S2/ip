package clippy.task;

/**
 * Basically a Task.
 *
 * @author chunzkok
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo instance.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo.
     * @return A string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo in CSV form.
     * @return A string representation of the ToDo in CSV form.
     */
    @Override
    public String getCsvString() {
        return String.format("T,%s", super.getCsvString());
    }
}
