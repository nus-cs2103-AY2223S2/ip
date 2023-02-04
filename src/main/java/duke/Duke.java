package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * Main Class which runs the whole chatbot.
 */
public class Duke {

    private StorageList storage;
    private TaskList tasks;

    /**
     * Constructor for class Duke.
     *
     * @param filePath Directory of the text file to be used for the saved commands.
     */
    public Duke(String filePath) {
        storage = new StorageList(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
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
            assert tasks != null : "Task List should always be initiated before "
                    + "execution commences.";
            return c.execute(tasks, storage);
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





