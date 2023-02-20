package duke.main;
import java.io.*;

/**
 * Main class that runs the app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises Storage, TaskList and Ui.
     * Previous tasks are loaded up.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTxtFile());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main method that updates the task file.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the app
     */
    public void run() {

        ui.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = ui.getCommand();
            Parser parser = new Parser();
            parser.parse(command, ui, tasks, storage);
        }
    }


}
