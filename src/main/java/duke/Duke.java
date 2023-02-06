package duke;
import java.io.IOException;

/**
 * Duke is the main class of this app and runs the program.
 */
public class Duke {

    public static final String DIRECTORY_PATH = "data";
    public static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList<Task> taskList;

    private Parser parser;
    private Ui ui;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH, DIRECTORY_PATH);
        try {
            this.taskList = storage.readFile();
        } catch (NeroException e) {
            this.taskList = new TaskList<Task>();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }




    /**
     * Creates the response that Duke replies to the user
     * @param input commands given by the user
     * @return Duke's response to the commands
     */
    String getResponse(String input) {
        String toPrint = "";
        try {
            toPrint = parser.parseCommand(input, taskList, ui);
            storage.saveFile(taskList);
        } catch (IOException e) {
            toPrint = ui.printFileNotFound();
        } catch (NeroException ne) {
            toPrint = ne.getMessage();
        } finally {
            return toPrint;
        }
    }
}


