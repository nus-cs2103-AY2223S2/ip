package command;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * ExitCommand class extends from Command class.
 */
public class ExitCommand extends Command {

    /**
     * Constructor.
     * 
     * @param input
     */
    public ExitCommand() {
        super(Commands.EXIT);
        super.toggleIsExit();
    }

    /**
     * Does nothing.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
