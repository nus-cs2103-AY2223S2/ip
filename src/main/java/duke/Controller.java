package duke;

import duke.commands.Command;

/**
 * Controller class that handles the flow of information in Duke
 */
public class Controller {

    private TaskList tasks;
    private UserInterface ui;
    private Storage storage;
    private Parser parser;
    private boolean isExited;

    Controller(UserInterface ui, Storage storage) {
        try {
            tasks = storage.recoverList();
        } catch (DukeException exception) {
            ui.showExceptionMessage(exception);
        }
        this.ui = ui;
        this.storage = storage;
        parser = new Parser();
        this.isExited = false;
    }

    public void runExecutionLoop() {
        while (!isExited) {
            try {
                String input = ui.getInput();
                Command command = parser.parse(input);
                executeCommand(command);
            } catch (DukeException exception) {
                ui.showExceptionMessage(exception);
            }
        }
    }

    public void executeCommand(Command command) throws DukeException {
        if (command == null) {
            isExited = true;
            return;
        }
        String result = command.execute(tasks);
        ui.displayResult(result);
        storage.saveTaskChangesToFile(tasks);
    }
}
