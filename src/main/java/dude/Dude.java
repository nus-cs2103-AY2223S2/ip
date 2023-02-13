package dude;

import java.io.FileNotFoundException;

import dude.command.Command;
import dude.exception.DudeException;
import dude.parser.Parser;
import dude.storage.Storage;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Handles the entire dude program
 */
public class Dude {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes Dude
     *
     * @param filePath Path to data file stored.
     */
    public Dude(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Dude program
     */
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
            } catch (DudeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DudeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Dude("data/tasks.txt").run();
    }

}
