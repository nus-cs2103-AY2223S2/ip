package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * Represents HelpCommand object that reply user guide.
 */
public class HelpCommand extends Command {

    /**
     * Implements and executes main logic of HelpCommand object.
     * Reply user with user guide.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     * @return Bot replies user guide.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.printGuide();
    }
}
