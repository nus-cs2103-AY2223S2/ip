package duke;

import duke.exceptions.DukeException;
import duke.storage.FileManagement;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Duke is a personal assistant bot that helps user to track tasks.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Duke {
    private FileManagement fileManager;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        // to manage saved data
        this.fileManager = new FileManagement();
        // load existing list of tasks; creates empty if does not exist
        this.tasks = new TaskList(fileManager.retrieve());
        // receives user input and run parser
        this.ui = new UI(this.tasks);
    }

    /**
     * Gets greeting message to be displayed to the user.
     * @return the greeting message in string.
     */
    public String getGreeting() {
        String logo = "DUKE";
        String greeting = "Hello i'm\n"
                + logo
                + "\nWhat can I do for you?";
        return greeting;
    }

    /**
     * Gets the appropriate response according to the user's input.
     * @param input the user's input.
     * @return information about the actions executed.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = this.ui.processInput(input);
        } catch (DukeException e) {
            return e.toString();
        }
        this.fileManager.save(tasks);
        return response;
    }

}
