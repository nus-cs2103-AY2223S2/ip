package duke;

/**
 * ToDo Class
 * subclass of Task
 */
public class ToDo extends Task {
    private final String taskType = "[T]";

    /**
     * Constructor of ToDo
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * toString() of ToDo
     * @return description of ToDo
     */
    @Override
    public String toString() {
        return taskType + super.toString();
    }
}
