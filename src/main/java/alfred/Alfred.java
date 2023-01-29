import alfred.ui.Ui;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.parser.Parser;
import alfred.exceptions.AlfredException;
import alfred.command.Command;

public class Alfred {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Alfred(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (AlfredException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayOpening();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlfredException e) {
                ui.displayError(e);
            }
        }
    }

    public static void main(String[] args) {
        Alfred alfred = new Alfred("data/alfred.txt");
        alfred.run();
    }
}
