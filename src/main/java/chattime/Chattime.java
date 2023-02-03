package chattime;

import chattime.command.Command;
import chattime.exception.ChattimeException;
import chattime.parser.Parser;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * A chatbot that receives user's input on various predetermined command types and performs relevant functions.
 */
public class Chattime {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    /**
     * Initializes a bot with ui objects a nd a storage space with provided path to store list.
     *
     * @param filePath Path to storage.
     */
    public Chattime(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Runs the bot and handles user's input.
     */
    String getResponse(String input) {
        String response = "";
        try {
            Command cmd = Parser.parse(input);

            if (cmd != null) {
                response = cmd.execute(ui, tasks, storage);
            }
        } catch (ChattimeException e) {
            return ui.printError(e.getMessage());
        }
        return response;
    }

    /**
     * Replies gretting message to user.
     *
     * @return Greeting words.
     */
    String greet() {
        return ui.initUi();
    }

    /**
     * Determines if the 'bye' command is given to a bot instance.
     *
     * @return The running status of current bot.
     */
    boolean checkRunningStatus() {
        return ui.getExecuteStatus();
    }
}
