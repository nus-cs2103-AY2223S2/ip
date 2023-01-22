import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.command.*;

<<<<<<< Updated upstream
=======
/**
 * Main Duke class which keep track a list of tasks
 */
>>>>>>> Stashed changes
class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

<<<<<<< Updated upstream
=======
    /**
     * Duke constructor that creates a new list with the specified input file directory
     *
     * @param filePath a file location that has all the commands to Duke
     */
>>>>>>> Stashed changes
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        int currIteration = 0;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(tasks, currIteration);
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                currIteration++;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./././text-ui-test/input.txt").run();
    }

}
