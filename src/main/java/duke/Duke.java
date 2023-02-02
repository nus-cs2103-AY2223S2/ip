//Reference from the given code provided on CS2103 module website
package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Duke Class that runs the whole chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * Method that runs the chatbot based on the commands given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command.isValidCommand(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                ui.showLoadingError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                storage.updateStorage(tasks);
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
