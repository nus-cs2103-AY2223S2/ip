import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dirPath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException err) {
            ui.firstTime = true;
            this.tasks = new TaskList();
        }
    }

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
        new Duke("./data/").run();
    }
}