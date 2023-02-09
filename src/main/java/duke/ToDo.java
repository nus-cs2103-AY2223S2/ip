package duke;

/**
 * Models ToDo which is a task.
 */
public class ToDo extends Task {
    /** String used to assign the name of the task. */

    protected String desc;

    /**
     * Constructor for the ToDo class.
     *
     * @param desc The name of the task.
     */
    public ToDo(String desc) {
        super(desc);
        this.desc = desc;
    }

    /**
     * Overloaded constructor for the ToDo class.
     *
     * @param desc The name of the task.
     * @param b The status of the task.
     */
    public ToDo(String desc, boolean b) {
        super(desc, b);
        this.desc = desc;
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
        if (super.isDone) {
            return "T,1," + desc;
        } else {
            return "T,0," + desc;
        }
    }
}
