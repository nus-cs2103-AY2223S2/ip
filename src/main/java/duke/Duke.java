package duke;

import duke.command.Command;
import duke.textui.TextUi;

/**
 * A chatbot named duke that can process the commands in command line format. The chatbot allows for the adding of
 * todo, deadline and event tasks into a task list which can be viewed. Furthermore, the tasks can be marked as done
 * or not done to keep track of the progress. The tasks can be deleted as well. Finally, the chatbot is able to save
 * and load the data of the tasks on start and ending of its operation.
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
     * Load a new chatbot with a new ui and storage. It obtains the data of the tasks stored if they are present. If
     * not, a new empty list of tasks is assigned instead.
     *
     * @param filePath The file path of where the tasks are stored
     */
    public Duke(String filePath) {
        textUi = new TextUi();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            textUi.showError(e.getMessage());
            taskList = new TaskList();
        }
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
