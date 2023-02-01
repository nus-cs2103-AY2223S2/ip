package duke.task;

import duke.DukeException;

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
     * Creates a todo object
     *
     * @param description Description of the todo task
     * @param isDone Completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo generate(String input) throws DukeException {
        // Cleans input and checks for description
        try {
            String description = input.trim()
                    .substring(5);
            return new Todo(description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Todo missing description");
        }
    }

    public static Todo load(String input, boolean isDone) throws DukeException {
        // Cleans input and checks for description
        input = input.trim();
        if (input.equals("")) {
            throw new DukeException("duke.task.Todo missing description");
        }
        return new Todo(input, isDone);
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

    @Override
    public String save() {
        return "T | " + getStatusIcon()
                + " | " + description;
    }
}
