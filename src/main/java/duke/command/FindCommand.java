package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to find items in the list
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
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
     * Displays each tasks from list
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @return instruction successfully set
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return taskList.findItem(input);
    }
}
