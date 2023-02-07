import exception.DukeException;
import parser.Parser;
import response.Response;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents the Duke class
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage(); // Create a Storage object
        this.taskList = storage.loadTaskList(); // Create a TaskList object based on saved list
        this.ui = new Ui(); // Create Ui object to handle interactions with user
    }

    /**
     * Gets a response from Duke based on the input of the user.
     * @param input the users input.
     * @return Duke's response to the user input as a String.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return ui.getExtStr();
        }
        // Parse the user input
        Parser parser = new Parser(input);
        String out;
        try {
            // Parsing the input returns a Response
            Response res = parser.parse();

            // Execute the Response to do what needs to be done and get an output message
            out = res.exec(taskList);

            // If the response is anything other than list, save the to do list
            if (!parser.getInputType().equals("LIST")) {
                storage.saveTaskList(taskList);
            }
        } catch (DukeException err) {
            return err.toString();
        }
        return out;
    }

    public String getWelcomeMessage() {
        return ui.getIntro();
    }
}
