package duke.task;

/**
 * Todo.
 *
 * @see Task
 */
public class Todo extends Task {

    /**
     * Constructs a todo.
     */
    public Todo(String title) {
        this(title, false);
    }

    /**
     * Constructs a todo with predefined done state.
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
        return "📝 " + super.toString();
    }
}
