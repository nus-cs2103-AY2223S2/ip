package task;

import exception.InvalidCommandInputException;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {
    /**
     * Constructor for Task
     * @param content The task content.
     */
    ToDo(String content) {
        super(content);
    }

    /**
     * Constructor for Task.
     * @param content The task content.
     * @param done Whether the task was completed.
     */
    ToDo(String content, boolean done) {
        super(content, done);
    }

    @Override
    public void update(String input) throws InvalidCommandInputException {

        if (input.length() == 0) {
            throw new InvalidCommandInputException("Empty argument", "todo");
        }

        updateContent(input);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}