package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * Represents ByeCommand object that handles exit of the bot program.
 */
public class ByeCommand extends Command {

    /**
     * Implements and executes main logic of ByeCommand object.
     * Exits the bot program with ending message.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     * @return Bot's reply to user's end chat command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        ui.endChat();
        return ui.exit();
    }
}
