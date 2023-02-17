package crystal.task;

/**
 * Represents the Todo task.
 *
 */
public class Todo extends Task {

    protected boolean isSet;

    /**
     * Constructor for Todo class.
     *
     * @param description Task description
     *
     */
    public Todo(String description) {
        super(description);
    }


    /**
     *  Returns the printed output shown in the list
     *
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     *  Returns the printed output format on the text file.
     *
     */
    @Override
    public String toExport() {
        return "T" + super.toExport();
    }
}
