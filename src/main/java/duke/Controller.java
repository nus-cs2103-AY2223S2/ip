package duke;

import duke.commands.Command;

public class Controller {

    private static final String COMMAND_SEPARATOR = " ";
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
        while(!isExited) {
            try {
                String input = ui.getInput();
                Command command = parser.parse(input);
                executeCommand(command);
            } catch (DukeException exception) {
                ui.showExceptionMessage(exception);
            }
        }
        ui.showExitMessage();
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
