package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import gui.Main;

/**
 * The ExitCommand class implements the action of exiting the program.
 *
 * @author Chia Jeremy
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.setResponse("Bye. Hope to see you again soon!");
        Main.exit();
    }
}
