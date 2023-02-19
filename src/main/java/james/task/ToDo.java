package james.task;

/**
 * The todo class.
 * A todo is a task that is listed on the taskList without a deadline.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param description The description of the ToDos task.
     */
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public String toStoreString() {
        return "T | " + super.toStoreString();
    }

}

