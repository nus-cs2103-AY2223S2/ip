package duke;
import duke.exceptions.NeroException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Duke is the main class of this app and runs the program.
 */
public class Duke {

    public static final String DIRECTORY_PATH = "data";
    public static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList taskList;

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
            this.taskList = new TaskList();
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
        try {
            String toPrint = parser.parseCommand(input, taskList, ui);
            storage.saveFile(taskList);
            return toPrint;
        } catch (IOException e) {
            return ui.printFileNotFound();
        } catch (NeroException ne) {
            return ne.getMessage();
        }
    }
}


