package duke;

import duke.command.Command;
import duke.textui.TextUi;

/**
 * A chatbot named duke that can process the commands in command line format.
 * The chatbot allows for the adding of todo, deadline and event tasks into a task list which can be viewed.
 * Furthermore, the tasks can be marked as done or not done to keep track of the progress. The tasks can be deleted
 * as well. Finally, the chatbot is able to save and load the data of the tasks on start and ending of its operation.
 * The user can check all of these commands with the help command.
 */
public class Duke {

    /**
     * Able to deal with the loading and storing of data of the respective tasks.
     */
    private final Storage storage;
    /**
     * Sends out the display of the respective tasks.
     */
    private final TextUi textUi;
    /**
     * The list of tasks that is being tracked by the chatbot.
     */
    private TaskList taskList;

    /**
     * Load a new chatbot with a new ui and storage with the file path provided.
     *
     * @param filePath The file path of where the tasks are stored
     */
    public Duke(String filePath) {
        textUi = new TextUi();
        storage = new Storage(filePath);
    }

    /**
     * Setups duke tasklist by reading the data from the file specified by the file path and returns error message.
     * Returns a string representing the error if there is any when reading the data.
     *
     * @return String representing the error message
     */
    public String setup() {
        String msg = "";
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            msg = textUi.showError(e.getMessage());
            taskList = new TaskList();
        }
        return msg;
    }

    /**
     * Gets the welcome message by the chatbot.
     *
     * @return The welcome message by the chatbot
     */
    public String getWelcome() {
        return textUi.showWelcome();
    }

    /**
     * Gets the response for the user input in a string representation to be displayed out by the chatbot.
     *
     * @param input The command input by the user
     * @return The string representation of the response based on the command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, textUi, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unknown command/error not caught!\n Please try again!";
        }
    }
}
