package james.command;

import james.JamesException;
import james.task.ToDo;

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
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof addToDoCommand) {
            return toDo.equals(((addToDoCommand) obj).toDo);
        }
        return false;
    }


}
