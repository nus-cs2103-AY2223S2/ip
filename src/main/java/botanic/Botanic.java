package botanic;

import botanic.command.Command;
import botanic.exception.BotanicException;
import botanic.gui.Gui;
import botanic.parser.Parser;
import botanic.storage.Storage;
import botanic.task.Task;
import botanic.task.TaskList;
import botanic.task.ToDo;

/**
 * Encapsulates the related fields and behavior of Botanic.
 */
public class Botanic {
    //@@author HmuuMyatMoe-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/website/admin/ip-w3.html
    //with minor modifications
    private Storage storage;
    private TaskList tasks;
    private Gui gui;
    private Parser parser;

    /**
     * Instantiates the Botanic program.
     *
     * @param dirPath The path to the directory that the storage file is located at.
     * @param fileName The name of the storage file.
     */
    public Botanic(String dirPath, String fileName) {
        storage = new Storage(dirPath, fileName);
        gui = new Gui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.read());
        } catch (BotanicException e) {
            tasks = new TaskList();
            e.printStackTrace();
        }
    }
    //@@author

    /**
     * Returns the welcome message.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return gui.getWelcome();
    }

    /**
     * Stores the tasks in a file in hard drive.
     */
    public void store() {
        Task[] tempTasks = { new ToDo("a") };
        storage.writeToFile(
                tasks.getTaskList().toArray(tempTasks));
    }

    /**
     * Parses the user input to get a Command, executes the Command.
     * Returns Botanic's response if command execution is successful.
     * Catches and handles BotanicException if there is any thrown.
     *
     * @param input The user input read.
     * @return A string representing Botanic's response to the user input.
     */

    public String getResponse(String input) {
        //@@author HmuuMyatMoe-reused
        //Reused from https://nus-cs2103-ay2223s2.github.io/website/admin/ip-w3.html
        //with minor modifications
        try {
            Command cmd = parser.parseCommand(input);
            return cmd.execute(tasks, storage, gui);
        } catch (BotanicException e) {
            return e.getMessage();
        }
        //@@author
    }
}
