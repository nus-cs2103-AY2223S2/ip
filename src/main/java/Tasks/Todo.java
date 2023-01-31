package tasks;

import exceptions.NoTaskDescriptionException;

/** 
 * This class represents a task to be done
 */
public class Todo extends Task {

    protected Todo(String name) throws NoTaskDescriptionException{
        super(name, "ToDo");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    protected String stringifyTaskToSave() {
        return "T|" + super.stringifyTaskToSave();
    }
}
