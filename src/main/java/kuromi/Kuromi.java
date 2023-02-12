package kuromi;

import javafx.stage.Stage;
import kuromi.command.Command;
import kuromi.exception.KuromiException;
import kuromi.parser.Parser;
import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * The main class of Kuromi where the application is instantiated through this class. A <code>Kuromi</code> object
 * has three attributes: Storage, TaskList, and Ui that is needed to run the application.
 */
public class Kuromi {
    /** Access to the storage of tasks in hard disk **/
    private Storage storage;
    /** Current task list **/
    private TaskList tasks;
    /** UI of the application **/
    private Ui ui;

    /**
     * kuromi.gui.component.MainWindow.kuromi.exceptions.KuromiException.Main constructor (for invocation by main method).
     * Get stored data from previous session.
     *
     * @param filePath The file path to the file stored with data from previous Kuromi session.
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

    public String getResponse(String inp) {
        try {
            Command c = Parser.parse(inp, ui, tasks);
            return c.execute(tasks, ui, storage);
        } catch (KuromiException e) {
            return e.getMessage();
        }
    }
}
