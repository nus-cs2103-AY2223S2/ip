import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * The main class that Duke is invoked on.
 */
public class Duke {

    /** Main storage used for Duke. */
    private Storage storage;
    /** Task's list to be used by Duke. */
    private TaskList tasks;
    /** UI for Duke. */
    private Ui ui;
    /** Parser for understanding commands. */
    private Parser parser;

    /**
     * Constructor for the main Duke class.
     *
     * @param fileName the name of the storage file which Duke will store its data in.
     */
    public Duke(String fileName) {
        ui = new Ui("Duke!");
        storage = new Storage(fileName);
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }

    /**
     * This is the main run method for Duke.
     */
    public void runDuke() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                parser.executeCommand(fullCommand, tasks, storage, ui);
            } catch (DukeException e) {
                e.getMessage();
            }
            isExit = parser.isExit(fullCommand);
        }
        assert isExit;
        ui.closeScanner();
        ui.printExit();
    }

    public String getDukeResponse(String input) {

        /* This is the string in response to the user input **/
        String dukeResponse;

        dukeResponse = parser.runCommand(input, tasks, storage, ui);

        assert !dukeResponse.equals("");

        return dukeResponse;
    }

    /**
     * This is the main method which invokes Duke to run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").runDuke();
    }
}
