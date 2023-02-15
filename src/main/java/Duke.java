import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;

/**
 * The type Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke for CLI
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                String s = c.execute(tasks, ui, storage);
                System.out.println(s);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns response from Duke for a specific user input
     * @param input
     * @return String of response from Duke for input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String s = c.execute(tasks, ui, storage);
            return s;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
