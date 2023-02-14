package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * Represents HelpCommand object that replies user guide.
 */
public class HelpCommand extends Command {

    /**
     * Implements and executes main logic of HelpCommand object.
     * Replies user with user guide.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The user guide.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.printGuide();
    }
}
