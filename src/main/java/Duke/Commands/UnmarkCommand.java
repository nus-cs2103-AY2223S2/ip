package Duke.Commands;

import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.changeState(input, "unmark");
        ui.printResponse(response);
    }
}
