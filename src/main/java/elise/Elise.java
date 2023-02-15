package elise;

import java.io.IOException;

import elise.commands.Command;
import elise.internal.Parser;
import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;

/**
 * Elise is a personal assistant chat-bot that help to keep track of various stuff.
 */
public class Elise {
    // Attribute
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Elise.
     *
     * @param filePath Path of initial data file.
     */
    public Elise(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (EliseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs Elise chat-bot.
     */

    public String getResponse(String input) {
        try {
            Command c = Parser.read(input);
            return c.execute(ui, taskList, storage);
        } catch (EliseException e) {
            return ui.showError(e);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }
}
