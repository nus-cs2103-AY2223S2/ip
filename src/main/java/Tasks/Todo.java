package tasks;

import java.time.LocalDate;

import exceptions.NoTaskDescriptionException;

/** 
 * This class represents a task to be done
 */
public class Todo extends Task {

    public Todo(String name) throws NoTaskDescriptionException{
        super(name, "ToDo");
    }

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

    @Override
    public String stringifyTaskToSave() {
        return "TODO|" + super.stringifyTaskToSave();
    }
}
