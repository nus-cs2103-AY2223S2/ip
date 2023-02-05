package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * ExitCommand class extends from Command class.
 */
public class ExitCommand extends Command {

    /**
     * Constructor.
     * @param input
     */
    public ExitCommand() {
        super(Commands.EXIT);
        super.toggleIsExit();
    }

    /**
     * Returns exit message.
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Todeloo!";
    }
}
