public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
            String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                ui.showLine();
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("ip/data/tasks.txt").run();
    }
}
