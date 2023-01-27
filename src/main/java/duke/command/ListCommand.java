package duke.command;

import duke.exceptions.TaskException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Gives command to display items in the list
 */
public class ListCommand extends Command {

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
     * Displays each tasks from list
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.printList();
    }
}
