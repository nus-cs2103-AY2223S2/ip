package duke.commands;

import duke.exceptions.DukeInvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
