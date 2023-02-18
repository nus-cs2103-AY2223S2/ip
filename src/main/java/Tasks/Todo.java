package tasks;

import java.time.LocalDate;

import exceptions.NoTaskDescriptionException;

/**
 * This class represents a Todo Task, which does have any date or time attached to it
 */
public class Todo extends Task {

    /**
     * Creates a todo task
     *
     * @param name {@inheritDoc}
     * @throws NoTaskDescriptionException
     */
    public Todo(String name) throws NoTaskDescriptionException {
        super(name, "ToDo");
    }

    /**
     * {@inheritDoc}
     *
     * @return false because todo task does not contain date
     */
    public boolean contains(LocalDate date) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringifyTaskToSave() {
        return "TODO|" + super.stringifyTaskToSave();
    }
}
