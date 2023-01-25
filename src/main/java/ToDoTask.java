public class ToDoTask extends Task{

    static final String INDICATOR = "[ToDo]";
    static final String INPUT_PREFIX = "todo ";
    static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating ToDo Task";
    ToDoTask(String name) throws DukeException {
        super(name);
    }

    static ToDoTask createToDo(String text) throws DukeException {
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
