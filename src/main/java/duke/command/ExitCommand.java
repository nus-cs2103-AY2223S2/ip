package duke.command;

import duke.exception.DukeUnknownCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the currently running program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor to create an exit command.
     *
     * @param fullCommand user input command.
     * @throws DukeUnknownCommandException indicate that an unknown command has been passed.
     */
    public ExitCommand(String[] fullCommand) throws DukeUnknownCommandException {
        if (fullCommand.length > 1) {
            throw new DukeUnknownCommandException(" Do you mean \"exit\"?");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}
