package Command;

import Task.TaskList;
import Task.Task;

/**
 * Command class for ToDo
 */
public class ToDoCommand implements Command {
    private Task newToDo;

    public ToDoCommand(Task t) {
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
