public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadContents());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getCommand();
                Command command = Parser.parseCommand(userInput, tasks);
                command.execute(tasks, ui, storage);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
}