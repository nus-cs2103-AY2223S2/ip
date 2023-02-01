package duke.main;

import duke.command.Command;

import java.io.IOException;

/**
 * Main class for Duke program
 */
public class Duke {
    private final Ui ui;
    private Tasklist tasks;
    private Storage storage;

    /**
     * Duke constructor to initialise Ui, storage and tasklist
     * @param path filepath to retrieve data from
     */
    public Duke(String path) {
        ui = new Ui();

        try {
            storage = new Storage(path);
            tasks = new Tasklist(storage.load());
        } catch (IOException ie) {
            ui.printIOException(ie);
            System.exit(0);
        } catch (DukeException de) {
            ui.printDukeException(de);
            tasks = new Tasklist();
        }
    }

    /**
     * method to start Duke program
     */
    public void run() {
        ui.printGreeting();

        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                shouldExit = command.isExit();
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printIOException(ie);
            }
        }
        ui.printBye();
        ui.closeScanner();
    }

    /**
     * main method of Duke program.
     * Initialise Duke and starts it with run()
     * @param args Args passed into main method
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
