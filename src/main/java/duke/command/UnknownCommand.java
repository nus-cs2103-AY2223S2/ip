package duke.command;

import duke.exception.DukeUnknownInputException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command {

    public UnknownCommand(String fullCommand) {

        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeUnknownInputException {
        throw new DukeUnknownInputException("Unknown input.");

    }

    @Override
    public boolean isExit() {

        return false;
    }
}
