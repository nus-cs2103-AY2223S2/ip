package duke.Task;

/**
 * The ToDo task which is to be stored by Duke.
 */
public class ToDo extends Task {
    
    /**
     * Constructor for a ToDo task.
     * @param name Name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String taskToData() {
        return String.format("[T] | %s | %s", this.getStatusIcon(), this.name);
    }

}
