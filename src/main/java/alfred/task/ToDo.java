package alfred.task;

/**
 * Child class of task that represents a task with just a description.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Get task type which is todo
     *
     * @return
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Get and return storage type
     *
     * @return
     */
    @Override
    public String getStorageDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
