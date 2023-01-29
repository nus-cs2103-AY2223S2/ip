package duke;

import util.*;


/**
 * Duke class to run and execute the program
 *
 * @author Merrick
 */
public class Duke {
    private static final String divider = "    ____________________________________________________________";
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Initialises the Duke class which handles the TaskList, User Interface
     * and Storage of Tasks
     * @param fileDir File Directory of duke.txt.
     * @param filePath File path of duke.txt.
     */
    public Duke(String fileDir, String filePath) {
        ui = new Ui();
        storage = new Storage(fileDir, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Takes in input from the user and execute the specified task while the
     * Duke program is running.
     */
    public void run() {
        boolean repeat = true;
        while (repeat) {
            String command = ui.nextInput();
            System.out.println(divider);
            try {
                repeat = Parser.handleGeneralCommand(command, tasks, storage);
            } catch (DukeException e) {
                System.out.println(e);
            }
            if (!repeat) {
                try {
                    storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                ui.closeCommand();
            }
            System.out.println(divider);
        }
    }

    /**
     * Main method to run and execute the Duke program
     * @param args Array of inputs into the command line
     */
    public static void main(String[] args) {
        new Duke("src/main/data/", "src/main/data/duke.txt").run();
    }
}
