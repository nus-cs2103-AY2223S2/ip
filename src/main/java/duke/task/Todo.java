package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Class constructor.
     * @param description Name of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Print the Todo task in desired format.
     * @return Todo task printed in format "[T][] task name".
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Save the Todo task in desired format.
     * @return Todo task saved in text file in format "T| |task name".
     */
    @Override
    public String toRecord() {
        return "T|" + super.toRecord();
    }
}
