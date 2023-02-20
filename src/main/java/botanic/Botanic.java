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
    private Storage storage;
    private TaskList tasks;
    private Gui gui;
    private Parser parser;

    /**
     * Instantiates the chatbot program.
     *
     * @param dirPath The path to the directory that the file is stored in.
     * @param fileName The name of the storage file.
     */
    public Botanic(String dirPath, String fileName) {
        this.storage = new Storage(dirPath, fileName);
        this.gui = new Gui();
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(this.storage.read());
        } catch (BotanicException e) {
            this.gui.printMsg(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets the welcome message.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return this.gui.getWelcome();
    }

    /**
     * Stores the tasks in a file in hard drive.
     */
    public void store() {
        Task[] t = { new ToDo("a") };
        this.storage.writeToFile(this.tasks.getTaskList().toArray(t));
    }

    /**
     * Parses the user input to get a Command, executes the returned Command and
     * catches and handles BotanicException if there is any thrown.
     *
     * @param input The user input read.
     * @return A string representing Botanic's response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = this.parser.parseCommand(input);
            return cmd.execute(this.tasks, this.storage, this.gui);
        } catch (BotanicException e) {
            return e.getMessage();
        }
    }
}
