package duke;

public class ToDo extends Task {
    /**
     * Class constructor.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo task
     *
     * @return the string representation of a todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
