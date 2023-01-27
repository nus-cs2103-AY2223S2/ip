package duke.task;

/**
 * Subclass of task representing a ToDo class
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, false, "T");
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
