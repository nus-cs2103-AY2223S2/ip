package duke.command;

import duke.task.ToDo;

public class AddToDoCommand extends Command {
    private ToDo toDo;

    public AddToDoCommand(ToDo task) {
        this.toDo = task;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddToDoCommand) {
            return toDo.equals(((AddToDoCommand) obj).toDo);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("AddToDoCommand{toDo=%s}", toDo);
    }

    @Override
    public void execute() {
        taskList.addTask(toDo);
        ui.printTaskAdded(toDo, taskList.getSize());
    }

}
