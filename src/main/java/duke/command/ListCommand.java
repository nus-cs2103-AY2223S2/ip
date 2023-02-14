package duke.command;

import duke.exception.DukeUnknownCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all available task given a task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor to create a list command.
     *
     * @param fullCommand user input.
     * @throws DukeUnknownCommandException indicate that an unknown command has been passed.
     */
    public ListCommand(String[] fullCommand) throws DukeUnknownCommandException {
        if (fullCommand.length > 1) {
            throw new DukeUnknownCommandException(" Do you mean \"list\"?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.responseToListCommand(task);
    }
}

