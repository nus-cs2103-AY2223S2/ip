package james.command;

import james.JamesException;
import james.task.ToDo;

/**
 * The command to add a todo to the task list.
 */
public class AddToDoCommand extends Command {
    private ToDo toDo;

    public AddToDoCommand(ToDo task) {
        this.toDo = task;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(toDo);
        ui.addTask(toDo, taskList.getSize());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddToDoCommand) {
            return toDo.equals(((AddToDoCommand) obj).toDo);
        }
        return false;
    }


}
