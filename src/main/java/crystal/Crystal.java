package crystal;

import crystal.command.Command;
import java.io.File;

/**
 * Represents the Crystal class.
 *
 */
class Crystal {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Crystal class.
     *
     * @param filePath filepath to load the file from
     *
     */

    public Crystal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (CrystalException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     * Shows the welcome message.
     * While isExit is false, loads the previous saved file and
     * takes in user commands for the list.
     *
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.saveFile(tasks);
                isExit = c.isExit();
            } catch (CrystalException e) {
                ui.showError(e);

            }
        }
    }


    /**
     * Main method which calls the run method.
     *
     */


    public static void main(String[] args) {
        String file2 = "/repos/Independentproject/myfiles/Crystal.txt";
        String base = "/repos/Independentproject";
        String relative = new File(base).toURI().relativize(new File(file2).toURI()).getPath();
        new Crystal(relative).run();
    }
}