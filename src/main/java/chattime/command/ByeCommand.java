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
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's end chat command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        ui.endChat();
        return ui.exit();
    }
}
