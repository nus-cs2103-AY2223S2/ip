package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * Represents Command object to handle user input's command.
 * Abstract method `execute` is to be implemented by AddCommand, ByeCommand, DeleteCommand, ListCommand and
 * MarkCommand classes.
 */
public abstract class Command {
    /**
     * Implements and executes main logic of Command object.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's command.
     */
    public abstract String execute(Ui ui, TaskList taskList, Storage storage);

}
