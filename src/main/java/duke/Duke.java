package duke;
import duke.command.Command;

/** The Duke class contains variables and methods related to running an instance of Duke. */
public class Duke {
    protected static final String PATH = "src/data/duke.txt";
    protected TaskList lst;
    protected final Storage storage;
    protected final Parser parser;
    protected final Ui ui;

    /**
     * Creates an instance of Duke.
     *
     * @param filePath path of the file containing data for Duke
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.lst = new TaskList();
            this.storage.loadFileInto(this.lst);
        } catch (DukeException e) {
            ui.showError(e.toString());
        }
    }

    /**
     * Reads user input and loads data from file into an instance of Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                ui.showLine();
                Command command = parser.parse(userInput);
                command.execute(this.lst, this.ui);
                storage.saveToFile(this.lst);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}
