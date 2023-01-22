package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Duke() throws DukeException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = storage.readFromFile();

        ui.printWelcomeMessage();
        String userInput = ui.readCommand();

        while (!userInput.equals("bye")) {
            try {
                Command command = parser.parseCommand(userInput);
                command.assign(taskList, ui);
                command.execute();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                userInput = ui.readCommand();
            }
        }
        storage.writeToFile(taskList);
        ui.printByeMessage();
    }
}
