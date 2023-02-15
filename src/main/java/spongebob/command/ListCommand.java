package spongebob.command;

import spongebob.exception.SpongebobUnknownCommandException;
import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Command to list all available task given a task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor to create a list command.
     *
     * @param fullCommand user input.
     * @throws SpongebobUnknownCommandException indicate that an unknown command has been passed.
     */
    public ListCommand(String[] fullCommand) throws SpongebobUnknownCommandException {
        if (fullCommand.length > 1) {
            throw new SpongebobUnknownCommandException(" Do you mean \"list\"?");
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

