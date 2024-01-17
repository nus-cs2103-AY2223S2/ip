package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The InvalidCommand class represents the action of an incorrect command.
 * Upon execution, produce some feedbacks to the user.
 *
 * @author Chia Jeremy
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.setResponse("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
