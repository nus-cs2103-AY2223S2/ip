import java.io.FileNotFoundException;

import duke.command.Command;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the program.
 */
public class Duke {

    private static final String DIR_PATH = "./data/";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke.
     * @param dirPath Directory path.
     */
    public Duke(String dirPath) {
        assert dirPath != null;
        this.ui = new Ui();
        this.storage = new Storage(dirPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException err) {
            ui.setFirstTime(true);
            this.tasks = new TaskList();
        }
    }

    /**
     * Creates a new Duke with a default directory path.
     */
    public Duke() {
        this(DIR_PATH);
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                String line = ui.readCommand();
                Command c = Parser.parse(line);
                c.execute(tasks, ui, storage);
                isBye = c.isBye();
            } catch (Exception err) {
                ui.showBunny();
                ui.showError(err);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DIR_PATH).run();
    }

    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception err) {
            return ui.showError(err);
        }
    }

}
