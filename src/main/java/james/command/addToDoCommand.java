package james.command;

import james.JamesException;
import james.task.ToDo;

/**
 * The command to add a todo to the task list.
 */
public class addToDoCommand extends Command {
    private ToDo toDo;

    public addToDoCommand(ToDo task) {
        this.toDo = task;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(toDo);
        ui.addTask(toDo, taskList.getSize());
    }

}
