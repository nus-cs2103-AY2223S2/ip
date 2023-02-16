package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;


/**
 * Deals with execution of 'delete'
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the required actions for 'delete' and generates its corresponding response.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.getTasks().get(index - 1);
        taskList.deleteTask(index - 1);
        int taskListSize = taskList.getTasks().size();
        ui.showDeleteTaskMessage(t, taskListSize);
    }

    /**
     * Checks if command is 'bye'
     * @return true if is 'bye', false otherwise
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
