package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * UnknownCommand - when user enters a Command and Duke
 *         does not understand the command.
 */
public class UnknownCommand extends Command {

    /**
     * Constructor
     */
    public UnknownCommand() {

    }

    /**
     * Display the UI
     * @return a message that tells the user that Duke does not understand
     *         the given command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCommandNotFound();
    }
}
