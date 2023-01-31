package fideline.task;

/**
 * Representation of a simple task to be completed.
 *
 * @author Fun Leon
 */
public class Todo extends Task {

    /**
     * Constructs a simple task object. Only has a description.
     *
     * @param description Title given to the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
