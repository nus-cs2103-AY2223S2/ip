/**
 * The ToDo class extends the Task class and represents a task that needs to be done.
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class
     *
     * @param name The name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
