
import duke.duke_exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.parser.Parser;
import duke.utility.storage.Storage;

/**
 * <h1>Duke Chatbot</h1> The Duke chatbot is a bot that is capable to keep track of tasks from the
 * users.
 * 
 * @author Brian Quek
 */

public class Duke {

    private TaskList tasks;

    public Duke() {
        this.tasks = Storage.readData();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            output += Parser.readCommand(input, tasks);
            Storage.writeData(tasks);
        } catch (DukeException e) {
            output += e.toString();
        }
        return output;
    }
}
