package duke.task;

/*
 * Represents a Todo task
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo instance
     * 
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon()+ "] " + this.description;
    }
}