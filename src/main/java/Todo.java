
/**
 * Contains information of a todo
 * Contains description of the todo
 */
public class Todo extends Task {

    /**
     * Creates a todo object
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns type of task, completion status, description of the task
     *
     * @return Type of task, completion status, description of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
