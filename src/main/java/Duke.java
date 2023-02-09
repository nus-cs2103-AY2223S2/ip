import command.Command;
import task.TaskManager;
import util.DukeException;
import util.FileManager;
import util.Parser;

/**
 * Represents the main logic flow of the chatbot.
 */
public class Duke {
    private TaskManager taskManager;
    private FileManager fileManager;
    private Parser parser;

    /**
     * Initialises the file manager, task manager, parser and
     * loads data from the hard disk.
     */
    public Duke() {
        this.taskManager = new TaskManager();
        this.fileManager = new FileManager();
        this.fileManager.loadDataToArrayList(this.taskManager);
        this.parser = new Parser(fileManager);
    }

    /**
     * Parses scanner input and executes the corresponding command to
     * generate a response from the chatbot.
     * @param input
     * @return String representation of the chatbot's response
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input.trim());
            String res = command.executeCommand(taskManager);
            return res;
        } catch (DukeException e) {
            return e.toString();
        } catch (NullPointerException e) {
            return e.toString();
        }
    }
}
