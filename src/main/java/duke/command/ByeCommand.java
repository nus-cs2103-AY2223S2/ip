package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that is
 * task to close the chat.
 */
public class ByeCommand extends Command {

    /**
     * Constructor of ByeCommand.
     * @param command Command of the user.
     */
    public ByeCommand(String[] command) {
        super(command);
    }

    /**
     * Method to execute the goodbye message from
     * Ui.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    /**
     * Method that signals the end of the
     * chat.
     * @return True.
     */
    public boolean isExit() {
        return true;
    }
}
