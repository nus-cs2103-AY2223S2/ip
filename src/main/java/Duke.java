import sys.Storage;
import sys.Ui;
import task.TaskList;

/**
 * Represents the whole Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = this.storage.load();
    }

    /**
     * Sets the context for the UI and start accepting input from the user.
     */
    public void run() {
        this.ui.setContext(this.storage, this.tasks);
        this.ui.acceptInput();
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}