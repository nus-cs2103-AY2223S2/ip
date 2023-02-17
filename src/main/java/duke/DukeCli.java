package duke;

import duke.common.Storage;
import duke.controller.CliController;
import duke.model.Model;

/**
 * Driver for the CLI version of the application.
 */
public class DukeCli {

    /**
     * Starts the driver.
     */
    public void start() {
        Model model = new Model(Storage.readTaskList());
        CliController.setModel(model);
        CliController.greet();
        boolean shouldExit = false;
        while (!shouldExit) {
            CliController.handleUserInput();
            shouldExit = CliController.shouldExit();
        }
        Storage.writeTaskList(model.getTaskList());
    }
}
