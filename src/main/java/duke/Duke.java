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
    private final Storage STORAGE;
    /**
     * The list of tasks that is being tracked by the chatbot.
     */
    private TaskList taskList;
    /**
     * Sends out the display of the respective tasks.
     */
    private final TextUi UI;

    /**
     * Load a new chatbot with a new ui and storage. It obtains the data of the tasks stored if they are present. If
     * not, a new empty list of tasks is assigned instead.
     *
     * @param filePath The file path of where the tasks are stored
     */
    public Duke(String filePath) {
        UI = new TextUi();
        STORAGE = new Storage(filePath);
        try {
            taskList = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            UI.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * The main processing of the commands that are sent to the chatbot.
     * The welcome text is shown first. Following that, the parser takes care of parsing all the commands to
     * respective classes to be interpreted and executed. If there is an exception, it is displayed for the user to
     * take note of when keying in their next command. Finally, an exit message is shown at the termination of the
     * chatbot and the tasks data are stored.
     */
    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            } catch (Exception e) {
                UI.showMsg("Unknown command/error not caught!\n Please try again!");
            } finally {
                UI.showLine();
            }
        }
    }

    public String getWelcome() {
        return UI.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(taskList, UI, STORAGE);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unknown command/error not caught!\n Please try again!";
        }
    }
}
