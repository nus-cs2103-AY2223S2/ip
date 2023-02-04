package duke;

/**
 * Command class for ToDo
 */
public class ToDoCommand implements Command {
    private Task newToDo;
    ToDoCommand(Task t) {
        newToDo = t;
    }
    @Override
    public String execute(TaskList taskList) {
        return taskList.add(newToDo);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
