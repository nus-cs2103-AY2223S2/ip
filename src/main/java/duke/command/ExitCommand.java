package duke.command;

import duke.Duke;

public class ExitCommand extends Command {

    @Override
    public ReturnCode execute(Duke duke) {
        duke.ui.println("Saving your list ... ");
        duke.storage.saveDataToFile();
        duke.ui.println("Goodbye!");

        return ReturnCode.EXIT;
    }
}
