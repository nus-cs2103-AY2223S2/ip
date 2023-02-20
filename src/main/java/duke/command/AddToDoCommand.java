package duke.command;

import duke.task.ToDo;

/**
 * Adds a todo to the task list.
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
        assert toDo != null : "ToDo cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        taskList.addTask(toDo);
        return ui.printTaskAdded(toDo, taskList.getSize());
    }

}
