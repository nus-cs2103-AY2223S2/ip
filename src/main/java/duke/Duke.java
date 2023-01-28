package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui = new Ui(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showInitMessage();
        Parser p = new Parser(tasks, ui);
        p.parse();
        storage.store(tasks);
    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke d = new Duke(filePath);
        d.run();
    }
}
