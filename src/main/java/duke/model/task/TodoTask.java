package duke.model.task;

/**
 * Represents a todo task in the application.
 */
public class TodoTask extends Task {

    private static final long serialVersionUID = -2700942093958172810L;

    /**
     * Creates a new {@code TodoTask} with the given description.
     *
     * @param description the given description
     * @throws DukeIllegalArgumentException if the description is empty
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TodoTask)) {
            return false;
        }
        return super.equals(obj);
    }
}
