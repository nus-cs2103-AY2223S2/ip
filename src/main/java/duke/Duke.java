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
     * @param filePath Contains file path to the saved duke.txt file
     * @param directoryPath Contains directory path to the saved duke.txt file
     */
    public Duke(String filePath, String directoryPath) {
        this.storage = new Storage(filePath, directoryPath);
        try {
            this.taskList = storage.readFile();
        } catch (NeroException e) {
            this.taskList = new TaskList<Task>();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public Duke() {}

    /**
     * Runs the program and catches IOException if file is not found
     * or NeroException if an error occurs, printing the error message
     * accordingly
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean toEnd = false;
        while (!toEnd) {
            String originalString = ui.readLine();
            try {
                toEnd = parser.parseCommand(originalString, taskList, ui);
                storage.saveFile(taskList);
            } catch (IOException e) {
                ui.printFileNotFound();
            } catch (NeroException ne) {
                ne.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH, DIRECTORY_PATH).run();
    }

    String getResponse(String input) {
        return "Duke read: " + input;
    }
}

