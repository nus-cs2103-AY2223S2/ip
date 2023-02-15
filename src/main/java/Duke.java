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


    public String getDukeResponse(String input) {

        /* This is the string in response to the user input **/
        String dukeResponse;

        dukeResponse = parser.runCommand(input, tasks, storage, ui);

        return dukeResponse;
    }

}
