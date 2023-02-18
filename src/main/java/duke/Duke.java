package duke;
import java.io.IOException;

import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is the main of the program that allows user to track upcoming tasks
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs new instance of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    /**
     * Runs a new instance of Duke
     *
     * @param args Command-line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Runs the program
     *
     * @throws IOException
     */
    public void run() throws IOException {
        ui.greetUser();

        try {
            this.tasks = new TaskList(storage.load());
            ui.notifySuccessfulLoad();
        } catch (DukeException e) {
            ui.printException(e);
        }

        awaitInput();
    }

    /**
     * Reads user input
     *
     * @throws IOException
     */
    private void awaitInput() throws IOException {

        String userInput = ui.getUserInput();

        while (!userInput.toUpperCase().equals("BYE")) {
            this.handleInput(userInput);
            userInput = ui.getUserInput();
        }

        this.endSession();
    }

    /**
     * Performs execution of user commands
     *
     * @param userInput
     */
    private void handleInput(String userInput) {

        try {
            Command c = Parser.parseCommand(userInput);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            this.ui.printException(e);
        }
    }

    /**
     * Terminates the program
     *
     * @throws IOException
     */
    private void endSession() throws IOException {
        storage.store(tasks);
        ui.endSession();
    }
}
