package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.readTasksFromFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean canContinue = true;
        while (canContinue) {
            try {
                String userCommandText = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parse(userCommandText);
                c.execute(this.tasks, this.ui, this.storage);
                canContinue = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) throws DukeException {
        new Duke(System.getProperty("user.dir") + "/duke.txt").run();
    }

}
