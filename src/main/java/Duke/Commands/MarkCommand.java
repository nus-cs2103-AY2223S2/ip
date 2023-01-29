package Duke.Commands;

import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.changeState(input, "mark");
        ui.printResponse(response);
    }
}
