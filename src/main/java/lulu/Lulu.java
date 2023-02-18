package lulu;

import lulu.command.Parser;
import lulu.command.Command;
import lulu.command.LoadCommand;

import lulu.exception.LuluException;

/**
 * Represents the chatbot, Lulu.
 * This class has its own TaskList, Storage and Ui to be passed as arguments for Commands.
 * The Commands will then manipulate the data in these arguments, or utilise the information from the arguments
 * to achieve their respective execution.
 */
public class Lulu {
    private static final String SAVE = "./data/lulu.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isSaveLoaded = false;

    public Lulu() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(SAVE);
    }

    /**
     * Returns the String response from the chatbot to be printed on the screen.
     *
     * @param fullCommand a command input by the user, that may or may not be a valid command
     * @return the String response to be printed
     */
    public String getResponse(String fullCommand) {
        if (!this.isSaveLoaded) {
            if (storage.isSavePresent()) {
                Command c = new LoadCommand();
                c.execute(tasks, ui, storage);
            }
            isSaveLoaded = true;
        }
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (LuluException e) {
            return ui.showContainer(e.toString());
        } catch (IndexOutOfBoundsException e) {
            return ui.showContainer(ui.showOutOfBounds());
        }
    }
}
