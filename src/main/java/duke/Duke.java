package duke;

import duke.command.Command;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.IncorrectFileFormatException;

import java.io.FileNotFoundException;

public class Duke { // a guy who receives user input, and gives duke reply

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String welcomeMsg;
    private Command prevCommand;

    /**
     * Duke Constructor. Sets up Duke, by creating Ui, Storage instance,
     * and loading stored tasks into TaskList.
     * Catches Exceptions and only exposes error message to GUI.
     *
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            this.welcomeMsg = ui.showWelcome();
        } catch (FileNotFoundException | IncorrectFileFormatException e) {
            tasks = new TaskList();
            this.welcomeMsg = ui.showWelcome(e);
        } catch (Exception e) { //catch all exception to prevent crashing
            tasks = new TaskList();
            this.welcomeMsg = ui.showWelcome(e);
        }
    }

    /**
     * Getter method for Welcome Message.
     *
     * @return Duke's Welcome Message, inclusive of error message if exception is thrown
     */
    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    /**
     * Returns Duke's reply to be displayed by Gui after sending user's message for processing.
     *
     * @param fullCommand String version of the command, user's input message
     * @return Duke's reply
     * @throws EmptyCommandException If param fullCommand is empty.
     */
    public String process(String fullCommand) throws EmptyCommandException{
        try {
            Command c = Parser.parse(fullCommand);
            prevCommand = c;
            return c.execute(tasks, ui, storage, prevCommand, this);
            // deal with bye
        } catch (EmptyCommandException e) {
            throw e;
        } catch (Exception e) {
            return ui.showError(e); // or e.getMessage()
        }
    }

    public void setTaskList(TaskList l) {
        tasks = l;
    }
}