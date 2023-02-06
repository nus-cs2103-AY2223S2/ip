package duke;

/**
 * This class is a ToDo task. It contains
 * the description of the task.
 */
public class ToDo extends Task {

    /**
     * Constructs a deadline with the given description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo with the given description, isDone and date.
     *
     * @param description Description of the ToDo task.
     * @param isDone      Whether the ToDo task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String convertToStorableString() {
        return String.format("T,%s,%s",
                this.isDone() ? "1" : "0",
                this.getDescription()
        );
    }

}
