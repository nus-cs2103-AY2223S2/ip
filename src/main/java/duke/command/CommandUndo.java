package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an undo command.
 */
public class CommandUndo extends Command {
    /**
     * Returns an undo command.
     * @param command full unparsed command.
     */
    public CommandUndo(String command) {
        super(command);
    }

    /**
     * Find previous task and undo the task.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Command lastCommand = taskList.getLastCommand();
        if (lastCommand != null) {
            return lastCommand.execute(taskList, ui, storage);
        }
        storage.writeArray(taskList);
        return "";
    }

    /**
     * Returns an Error command.
     * @param taskList contains the task list.
     * @return error command.
     */
    @Override
    public Command inverseCommand(TaskList taskList) {
        return new CommandError("", "You cannot undo an undo command.");
    }
}
