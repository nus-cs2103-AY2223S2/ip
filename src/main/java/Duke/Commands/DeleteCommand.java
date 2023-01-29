package Duke.Commands;

import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        if (input.equals("")) {
            throw new DukeEmptyInputException();
        }
        String response = tasks.delete(input);
        ui.printResponse(response);
    }
}
