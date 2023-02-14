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
    private boolean isEnd;

    /**
     * Initialises new instance of Duke.
     *
     * @param filePath The file path in String of the file where memory is stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.isEnd = false;
    }

    public String getPreviousTaskResponse() {
        String startingMessage = Ui.welcomeResponse;
        String askForTask = Ui.askForTaskResponse;
        String loadMessage = "";
        try {
            this.tasks = new TaskList(storage.loadData());
            loadMessage = Ui.successfulLoadResponse;
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
            loadMessage = Ui.unsuccessfulLoadResponse;
        }
        String response = Ui.addDoubleLineBreak(startingMessage, Ui.instructionResponse, loadMessage,
                Ui.listTaskResponse(this.tasks), askForTask);
        return response;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    /**
     * Returns appropriate response to user input.
     *
     * @param input The String input of user.
     * @return String The String response of Duke.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            isEnd = c.isExit();
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
