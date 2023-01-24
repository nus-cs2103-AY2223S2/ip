package duke;

import java.io.File;
import java.io.IOException;

import command.Command;
import exceptions.DukeException;
import tasks.TaskList;


/**
 * Main class for Duke
 */

class Duke {

    public static final String DIRECTORY_NAME = System.getProperty("user.dir") + File.separator + "data";
    public static final String FILE_NAME = "duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * constructor for duke. Once duke gets initiated, create duke.duke.Ui object, create storage
     * @param directory
     * @param filePath
     */

    public Duke(String directory, String filePath) {
        ui = new Ui();
        storage = new Storage(directory, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            // print some error
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * runs the program until isExit turns true.
     * Handles printing of messages
     */
    public void run() {
        System.out.println(Ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                // ui should scan for input.
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        new Duke(DIRECTORY_NAME, FILE_NAME).run();
    }
}