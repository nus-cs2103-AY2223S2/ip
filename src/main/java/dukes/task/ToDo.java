package dukes.task;

/**
 * Subclass of Task that handles tasks with "todo" label.
 */
public class ToDo extends Task {

    /**
     * Constructor of ToDo class.
     *
     * @param taskName name (main content) of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.tag = "T";
    }

    /**
     * Constructor of ToDo class.
     *
     * @param taskName name (main content) of the task.
     * @param isDone specifies if the task has been done or not.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
        this.tag = "T";
    }

    /**
     * Returns a string containing {tag "T" for todo task} +
     * {if the task is completed} + the content of the task.
     *
     * @return a string showing its a todo task,
     * if the task is completed and its content.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
