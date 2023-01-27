import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
            Duke duke = new Duke("./data/test.txt");
            duke.run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSepLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showSepLine();
            }
        }
    }

}

