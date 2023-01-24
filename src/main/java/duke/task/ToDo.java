package duke.task;

/**
 * Child class of task that represents a task with just a description.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String eventType() {
        return "T";
    }

    @Override
    public String getStorageDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
