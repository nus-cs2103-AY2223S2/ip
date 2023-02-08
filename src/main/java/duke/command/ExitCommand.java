package duke.command;

import duke.Duke;

/**
 * Handles a request to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public ReturnCode execute(Duke duke) {
        duke.ui.println("Saving your list ... ");
        duke.storage.saveDataToFile();
        duke.ui.println("Goodbye!");

        return ReturnCode.EXIT;
    }
}
