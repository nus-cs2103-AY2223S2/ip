package duke.task;

/**
 * Subclass of task representing a ToDo class
 */
public class ToDo extends Task {
    private static final String TYPE = "T";
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TYPE);
    }

    /**
     * Prints the string representation of a ToDo class
     *
     * @return string representation of a ToDo class
     */
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
