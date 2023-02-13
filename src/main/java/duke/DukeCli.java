package duke;

import duke.common.Storage;
import duke.controller.CliController;
import duke.model.Model;

public class DukeCli {

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
