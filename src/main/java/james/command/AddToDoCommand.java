package james.command;

import james.JamesException;
import james.task.ToDo;

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

}
