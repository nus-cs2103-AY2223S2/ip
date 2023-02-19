package duke.tasks;

/**
 * A class to represent a Todo task.
 */
public class ToDo extends Task {
    private static final long serialVersionUID = -8037857826265085451L;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("  [T]%s", super.toString());
    }
}
