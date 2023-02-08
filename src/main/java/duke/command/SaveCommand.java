package duke.command;

import duke.Duke;
import duke.DukeException;

public class SaveCommand extends Command {
    @Override
    public ReturnCode execute(Duke duke) throws DukeException {
        duke.storage.saveDataToFile();
        duke.ui.println("Your list have been saved.");

        return ReturnCode.SUCCESS;
    }
}
