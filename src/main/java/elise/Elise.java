package elise;

import java.io.IOException;

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
     * @throws EliseException if filePath is invalid.
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
     *
     * @throws IOException Unexpected IOException
     */

    public String getResponse(String input) {
        try {
            Command c = Parser.read(input);
            return c.execute(ui, taskList, storage);
        } catch (EliseException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "boomed";
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Driver function.
     *
     * @param args Command line arguments
     * @throws EliseException If filePath is invalid
     * @throws IOException Unexpected IOException
     */
    public static void main(String[] args) {
        Elise elise1 = new Elise("/list.txt");
    }
}
