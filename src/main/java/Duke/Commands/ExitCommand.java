package Duke.Commands;

import Duke.DukeExceptions.DukeInvalidInputException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        if (!input.equals("")) {
            throw new DukeInvalidInputException("Say bye properly by typing only bye!");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
