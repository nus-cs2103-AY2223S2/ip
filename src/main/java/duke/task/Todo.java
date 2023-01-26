package duke.task;

import duke.DukeException;

public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description.trim(), TaskIcon.TODO);
        if (this.description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
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
