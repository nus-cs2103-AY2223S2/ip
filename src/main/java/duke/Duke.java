package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * <h1>Duke Task Manager</h1>
 * The Duke program a that the creation of tasks such as Todo, Event and
 * Deadline. The program allows to listing of those added tasks and mark them as
 * done and not done, it also allows deletion of wrongly added task.
 *
 * @author Leng Wei Cong, Justin
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
    * Constructor.
    * @param filePath the path to the text file that stores the tasks
    */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Another constructor for JavaFX
     */
    public Duke() {
        Duke duke = new Duke("data/duke.txt");
        this.ui = duke.ui;
        this.storage = duke.storage;
        this.tasks = duke.tasks;
    }

    /**
    * Runs Duke.
    */
    public void run() {
        this.ui.showWelcome();
        this.handleRequest();
        this.ui.showExit();
    }

    /**
    * Handles the request from user input.
    */
    public void handleRequest() {
        while (true) {
            String fullcommand = ui.readCommand();
            Command command = Parser.parse(fullcommand);

            // Breaks when is an exit command
            if (command.isExit()) {
                break;
            }

            // Executes command
            command.execute(tasks, ui, storage);

            ui.showDivider();
        }
    }

    /**
    * Prints whatever the user inputs.
    * @param input the user input
    */
    public void echo(String input) {
        ui.showMessage(input);
    }

    /**
     * Generates a response to user input.
     * @param input the user input
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);

        // Breaks when is an exit command
        if (command.isExit()) {
            return "Todeloo!";
        }

        // Executes command
        return command.execute(this.tasks, this.ui, this.storage);
    }

    /**
    * This is the main method which makes use of run method.
    * @param args Unused.
    */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
