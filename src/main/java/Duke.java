import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String fileName){
        ui = new Ui("Duke!");
        storage = new Storage(fileName);
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            //UI can show loading error
            e.getMessage();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        boolean isValid;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                parser.executeCommand(fullCommand, tasks, storage, ui);
            } catch (DukeException e) {
                e.getMessage();
            }
            isExit = parser.checkForExit(fullCommand);
        }
        ui.closeScanner();
        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
