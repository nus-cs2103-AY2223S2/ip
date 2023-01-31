package duke.command;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to mark item as complete
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Initialises mark class
     *
     * @param index task sequences in task list
     */
    public MarkCommand(int index) {
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
     * Marks task items
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @throws TaskException displays error messages
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {

        return taskList.markItem(index);
    }
}
