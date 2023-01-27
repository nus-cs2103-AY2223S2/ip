package duke;
//
import java.io.IOException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        Ui.showLogo();
        Ui.showWelcome();

        String fullCommand;
        do {
            fullCommand = ui.readCommand();
            Parser.parse(fullCommand, tasks);
        } while(!fullCommand.equals("bye"));
        storage.updateFile(tasks);
        Ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
