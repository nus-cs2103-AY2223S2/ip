package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents
 * an invalid command.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class NoCommand extends Command {

    /**
     * Constructor of NoCommand.
     * @param command Command from the user.
     */
    public NoCommand(String[] command) {
        super(command);
    }

    /**
     * Method to call the unknownMsg method
     * from Ui.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.unknownMsg();
    }
}
