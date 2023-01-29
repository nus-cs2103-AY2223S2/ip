package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents the
 * command to show the current TaskList.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class ListCommand extends Command {

    /**
     * Constructor of ListCommand.
     * @param command Command from the user.
     */
    public ListCommand(String[] command) {
        super(command);
    }

    /**
     * Method to print the TaskList to console.
     * @param tasks List of tasks.
     * @param ui Ui of the chat.
     * @param storage Storage of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
