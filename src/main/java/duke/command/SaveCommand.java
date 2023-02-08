package duke.command;

import duke.Duke;

/**
 * Handles a request to save the tasklist.
 */
public class SaveCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public ReturnCode execute(Duke duke) {
        duke.storage.saveDataToFile();
        duke.ui.println("Your list have been saved.");

        return ReturnCode.SUCCESS;
    }
}
