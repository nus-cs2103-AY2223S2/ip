package duke.tasks;

/**
 * The Todo object that extends Task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo object.
     *
     * @param description The Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
