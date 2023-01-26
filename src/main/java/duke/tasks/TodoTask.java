package duke.tasks;

import duke.DukeException;

public class TodoTask extends Task {

    static final String INDICATOR = "[ToDo]";
    static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating ToDo duke.tasks.Task";
    TodoTask(String name) throws DukeException {
        super(name);
    }

    static TodoTask createToDo(String text) throws DukeException {
        String taskName = text.substring(INPUT_PREFIX.length());
        if (taskName.isEmpty()) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
        return new ToDoTask(taskName);
    }
    @Override
    public String toString() {
        return INDICATOR + super.toString();
    }
}
