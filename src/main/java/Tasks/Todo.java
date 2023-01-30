package tasks;

import exceptions.NoTaskDescriptionException;

public class Todo extends Task {

    protected Todo(String name) throws NoTaskDescriptionException{
        super(name, "ToDo");
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    protected String taskToSave() {
        return "T|" + super.taskToSave();
    }
}
