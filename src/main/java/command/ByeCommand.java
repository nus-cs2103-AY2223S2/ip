package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents Exit Command
 */
public class ByeCommand extends Command{
    /**
     * Returns Exit message
     * @param taskList TaskList object
     * @param storage Storage object
     * @param ui Ui object
     * @return Exit message
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.byeMsg();
    }
}
