package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the Duke program.
 * The Duke program is a task manager that allows users to add, delete and mark
 * tasks as done.
 */
public class Duke {

    /**
     * The main method of the Duke program.
     */
    public Duke() throws DukeException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList taskList = storage.readFromFile();

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
