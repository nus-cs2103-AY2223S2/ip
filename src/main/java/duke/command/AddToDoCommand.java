package duke.command;

import duke.task.ToDo;

/**
 * The command to add a todo to the task list.
 */
public class AddToDoCommand extends Command {
    private final ToDo toDo;

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
    public String execute() {
        taskList.addTask(toDo);
        return ui.printTaskAdded(toDo, taskList.getSize());
    }

}
