package seedu.duke;

import java.time.LocalDate;

/**
 * A Todo Task.
 */
public class Todo extends Task {

    private LocalDate dateCreated;

    /**
     * Constructor for Todo.
     *
     * @param id id of this Task
     * @param task task description
     */
    public Todo(int id, String task) {
        super(id, task);
        dateCreated = Duke.getCurrDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {
        return this.isDone() ? "[T][x] " + this.getTask() : "[T][ ] " + this.getTask();
    }
}
