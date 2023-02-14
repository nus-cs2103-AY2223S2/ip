package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an error command.
 */
public class CommandError extends Command {
    private final String output;
    /**
     * Returns a bye command.
     * @param command full unparsed command.
     */
    public CommandError(String command, String output) {
        super(command);
        this.output = output;
    }

    /**
     * Sends error message to the user.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return this.output;
    }
}
