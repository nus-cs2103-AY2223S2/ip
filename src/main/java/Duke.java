package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;

/**
 * Represents a Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isEnd;

    /**
     * Initialises new instance of Duke.
     *
     * @param filePath The file path in String of the file where memory is stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isEnd = false;
    }

    public String getPreviousTaskResponse() {
        String startingMessage = ui.welcomeResponse();
        String askForTask = ui.askForTaskResponse();
        try {
            this.tasks = new TaskList(storage.loadData());
            return startingMessage + "\n" + ui.successfulLoadResponse() + "\n" + ui.listTaskResponse(this.tasks)
                    + "\n" + askForTask;
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
            return startingMessage + "\n" + ui.loadingErrorMessage() + "\n" + ui.listTaskResponse(this.tasks) + "\n"
                    + askForTask;
        }
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    /**
     * Returns appropriate response to user input.
     *
     * @param input The String input of user.
     * @return String The String reponse of Duke.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            isEnd = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DateTimeParseException e2) {
            return ui.unreadableCommandErrorMessage();
        } catch (IllegalArgumentException e3) {
            return ui.incompleteCommandErrorMessage();
        } catch (ArrayIndexOutOfBoundsException e3) {
            return ui.incompleteCommandErrorMessage();
        }
    }
}
