package duke.task;

/**
 * Represents a Task without deadline or duration.
 *
 * @author Karen
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Todo) {
            Todo x = (Todo) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }

}
