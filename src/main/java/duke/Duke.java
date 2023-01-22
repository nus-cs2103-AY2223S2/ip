package duke;

import java.io.File;

import duke.Commands.Command;
import duke.Exceptions.DukeException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
/**
 * Main Duke class that drives the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ui.showLoading(filePath);
            tasks = new TaskList(storage.loadTasks());
            ui.showSuccessfulLoad(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     **/
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.closeScanner();
    }

    /**
     * Main method to initialize Duke.
     * 
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        String filePath = String.format("data%sduke.txt", File.separator);
        new Duke(filePath).run();
    }
}