package duke.tasks;

import duke.ui.Ui;
import duke.exceptions.TaskException;

/**
 * Represents a task to be done
 */
public class Todo extends Task {

    /**
     * Initialises to-do class
     *
     * @param name name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(String input) throws TaskException {
        if (input.length() <= 5) {
            Ui.error("todo");
        }
        System.out.println("You are now updating item in To-do task");
        super.updateTask(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
