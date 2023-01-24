import sys.Storage;
import sys.Ui;
import task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = this.storage.load();
    }

    public void run() {
        this.ui.setContext(this.storage, this.tasks);
        this.ui.acceptInput();
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}