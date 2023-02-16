package duke.task;

import duke.DukeException;

/**
 * Class representing a Todo.
 */
public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description.trim(), TaskIcon.TODO);
    }
    public Todo(String description, String tasks) throws DukeException {
        super(description.trim(), TaskIcon.TODO, tasks);
    }

    /**
     * Represent duke.task.Todo as a string
     * @return String representation of a duke.task.Todo
     */
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
