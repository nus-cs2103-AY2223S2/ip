import duke.functions.Storage;
import duke.tasks.TaskList;
import duke.functions.Ui;

public class Duke {
    protected static final String filePath = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.readDatabase(tasks);
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        TaskList list = new TaskList();
        d.storage.readDatabase(list);
        d.ui.run(list);
    }
}
