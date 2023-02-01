package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main Class which runs the whole chatbot.
 */
public class Duke {

    private StorageList storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for class Duke.
     *
     * @param filePath Directory of the text file to be used for the saved commands.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new StorageList(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(String.valueOf(e));
            tasks = new TaskList();
        }
    }


    /**
     * Method which starts the program to output the various messages and checks the commands.
     *
     * @param userInput The input command of the user.
     * @return String The output of the command from the input.
     */
    public String run(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            return "Sorry i did not understood that command!";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Pls fill in the command accordingly";
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            storage.updateStorage();
        }
    }

}





