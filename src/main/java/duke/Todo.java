package duke;

import duke.task.Task;

public class Todo extends Task {

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