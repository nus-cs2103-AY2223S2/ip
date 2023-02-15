package duke;

/**
 * Models ToDo which is a task.
 */
public class ToDo extends Task {
    /** String used to assign the name of the task. */

    protected String taskDesc;

    /**
     * Constructor for the ToDo class.
     *
     * @param taskDesc The name of the task.
     */
    public ToDo(String taskDesc) {
        super(taskDesc);
        this.taskDesc = taskDesc;
    }

    /**
     * Overloaded constructor for the ToDo class.
     *
     * @param taskDesc The name of the task.
     * @param taskStatus The status of the task.
     */
    public ToDo(String taskDesc, boolean taskStatus) {
        super(taskDesc, taskStatus);
        this.taskDesc = taskDesc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String asCsv() {
        if (super.taskStatus) {
            return "T,1," + taskDesc;
        } else {
            return "T,0," + taskDesc;
        }
    }
}
