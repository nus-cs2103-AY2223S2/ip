package spongebob.command;

import spongebob.exception.SpongebobUnknownCommandException;
import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Command for exiting the currently running program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor to create an exit command.
     *
     * @param fullCommand user input command.
     * @throws SpongebobUnknownCommandException indicate that an unknown command has been passed.
     */
    public ExitCommand(String[] fullCommand) throws SpongebobUnknownCommandException {
        if (fullCommand.length > 1) {
            throw new SpongebobUnknownCommandException(" Do you mean \"exit\"?");
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
