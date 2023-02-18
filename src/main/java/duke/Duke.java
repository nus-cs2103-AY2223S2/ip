package duke;

import duke.commands.Command;
import duke.tasktype.TaskList;
import javafx.application.Platform;

/**
 * The main class of this task list management bot.
 */
public class Duke {
    private TaskList lst;
    private Ui ui;
    private Storage storage;

    public Duke() {
    }

    /**
     * The default and only constructor of Duke class.
     * Initializes UI, Storage, and the list to store tasks.
     *
     * @param filePath the path of the file to store the task list
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.lst = storage.load();
    }

    /**
     * Deals with the user commands.
     *
     * @param cmd the command typed by the user
     * @return the response from the bot
     */
    public String dealWithCommand(String cmd) {
        Command command = Parser.parse(cmd);
        if (command.isBye()) {
            Platform.exit();
        }
        return command.operate(lst, ui, storage);
    }

    /**
     * Gets the response from the bot.
     *
     * @param input the input from the user
     * @return the output from the bot
     */
    public String getResponse(String input) {
        return dealWithCommand(input);
    }
}
