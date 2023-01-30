package duke;

import duke.command.Command;
import duke.command.ListCommand;

/**
 * The main class for the Duke program.
 */
public class Duke {

    // Utility class instances
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList list;

    /**
     * Constructor for Duke class.
     * @param filePath Path to file containing stored Duke data (task list). Must not have extension.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Runs the main Duke program.
     */
    public void run() {
        ui.open();
        try {
            list = new TaskList(storage.load());
            if (list.getSize() != 0) {
                new ListCommand().execute(ui, list, "");
            }
        } catch (DukeException de) {
            ui.pixlPrintException(de);
            ui.pixlPrint("Creating a new list...");
            list = new TaskList();
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandStr = ui.getNewCommand();
                Command command = parser.parse(commandStr);
                command.execute(ui, list, commandStr);
                isExit = parser.getIsExit();
            } catch (DukeException de) {
                ui.pixlPrintException(de);
            }
        }
        try {
            storage.save(list.getList());
        } catch (DukeException de) {
            ui.pixlPrintException(de);
        }
        ui.close();

    }

    /**
     * Main method of Duke. Runs the program with default settings.
     * @param args Default main command-line args.
     */
    public static void main(String[] args) {
        new Duke("data/Duke").run();
    }
}
