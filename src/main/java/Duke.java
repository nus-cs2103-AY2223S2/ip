import tasklist.TaskList;
import storage.Storage;
import ui.Ui;
import command.*;
import parser.Parser;
import exceptions.DukeException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = storage.load();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {

        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextInput();
                ui.showLine();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, storage, ui);
                isExit = c.isGoodbye();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

}
