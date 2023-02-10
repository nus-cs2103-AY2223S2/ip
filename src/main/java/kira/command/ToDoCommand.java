package kira.command;

import kira.storage.TaskList;
import kira.task.ToDo;
import kira.ui.Ui;

/**
 * Command for TODO.
 */
public class ToDoCommand extends Command {

    private ToDo task;

    /**
     * Constructs an executable to store a todo task.
     *
     * @param task todo task to be stored
     */
    public ToDoCommand(ToDo task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        taskList.store(task);
        ui.storeTaskMsg(task, taskList.getTotal());
        return true;
    }

}
