package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;
import luffy.exception.LuffyException;
import luffy.task.Task;

/**
 * The DeleteCommand class encapsulates the variables and methods related to Delete commands.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_COMMAND = "delete";
    private final int index;

    /**
     * Constructor creates an instance of DeleteCommand.
     * @param index int index of task to be deleted.
     */
    public DeleteCommand(int index) {
        super(DELETE_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws LuffyException {
        Task task = taskList.getTask(this.index);
        taskList.deleteTask(this.index);
        return ui.showDeletedTask(task, taskList.getSize());
    }
}
