package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class for Bye command.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for command "Bye".
     */
    public ByeCommand() {}

    /**
     * Executes a Bye command bye replying with a farewell message.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        return ui.farewellMessage();
    };
}
