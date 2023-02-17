package duke.logic.task;

import duke.DukeException;

/**
 * Todo class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo object.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo object with boolean
     *
     * @param description Description of task.
     * @param isDone Whether task is marked complete
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory method to create Todo object.
     *
     * @param text String to be processed into Todo object.
     * @return Todo object.
     * @throws DukeException If input String length is < 1.
     */
    public static Todo create(String text) throws DukeException {
        if (text.length() < 1) {
            throw new DukeException();
        } else {
            return new Todo(text.substring(1));
        }
    }

    /**
     * Factory method to create Todo object with boolean input.
     *
     * @param text String to be processed into Todo object.
     * @param isDone Whether Todo object should be mark as completed.
     * @return Todo object.
     * @throws DukeException If input String length is < 1.
     */
    public static Todo create(String text, Boolean isDone) throws DukeException {
        if (text.length() < 1) {
            throw new DukeException();
        } else {
            return new Todo(text.substring(1), isDone);
        }
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
