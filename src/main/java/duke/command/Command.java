package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class that acts as the
 * superclass for the numerous
 * command
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public abstract class Command {
    protected String[] command;

    /**
     * Constructor of Command
     * @param command Command from the user.
     */
    public Command(String[] command) {
        this.command = command;
    }

    /**
     * Method to execute the command.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * Method to keep the chat running
     * if the command is not bye.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
