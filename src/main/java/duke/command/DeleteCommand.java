package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to delete item
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initialises delete class
     *
     * @param index task sequences in task list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Exits duke if it detects bye command
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes task actions
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @return instruction successfully set
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {

        return taskList.deleteTask(index);
    }
}
