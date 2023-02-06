package duke.task;

/**
 * Todo
 */
public class Todo extends Task {

    /**
     * Constructor
     */
    public Todo(String title) {
        this(title, false);
    }

    /**
     * Constructor with predefined done state
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * {@inheritDoc}
     */
    public String toCsv() {
        return "T," + super.toCsv() + ",,";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
