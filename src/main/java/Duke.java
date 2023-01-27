import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the Duke chatbot.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Tasks tasks;

    /**
     * Creates a new Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new Tasks();
        this.storage = new Storage();
        this.storage.load(this.tasks);
    }

    /**
     * Initiates the process for Duke to load and read data when called.
     */
    public void run() {
        //print Duke logo
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            Ui.printDivider();
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            if (c instanceof ExitCommand) {
                isExit = true;
            }
            c.execute(this.tasks, this.ui, this.storage);
            Ui.printDivider();
        }

        ui.showGoodbyeMessage();
        this.storage.save(this.tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
