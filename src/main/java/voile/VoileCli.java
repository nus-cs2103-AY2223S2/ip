package voile;

import voile.common.Storage;
import voile.controller.CliController;
import voile.model.Model;

/**
 * Driver for the CLI version of the application.
 */
public class VoileCli {

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
