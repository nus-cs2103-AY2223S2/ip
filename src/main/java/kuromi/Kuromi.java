package kuromi;

import javafx.stage.Stage;
import kuromi.command.Command;
import kuromi.task.TaskList;

/**
 * The main class of Duke where the application is instantiated through this class. A <code>Duke</code> object
 * has three attributes: Storage, TaskList, and Ui that is needed to run the application.
 */
public class Kuromi {
    /** Access to the storage of tasks in hard disk **/
    private Storage storage;
    /** Current task list **/
    private TaskList tasks;
    /** UI of the application **/
    private Ui ui;

    private Stage stage;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor (for invocation by main method).
     * Get stored data from previous session.
     *
     * @param filePath The file path to the file stored with data from previous Duke session.
     */
    public Kuromi(java.nio.file.Path filePath, Stage stage) {
        ui = new Ui(stage);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KuromiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke application to interact with the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KuromiException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
            ui.showEnter();
        }
    }

    String getResponse(String inp) {
        try {
            Command c = Parser.parse(inp, ui, tasks);
            return c.execute(tasks, ui, storage);
        } catch (KuromiException e) {
           return e.getMessage();
        }
    }
}
