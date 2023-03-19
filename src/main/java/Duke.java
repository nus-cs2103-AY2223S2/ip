package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    /**
     * Loads tasks and returns whether this action is successful.
     *
     * @return boolean The boolean indicating if load was successful.
     */
    private boolean hasLoadedSuccessfully() {
        try {
            this.tasks = new TaskList(storage.loadData());
            return true;
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
            return false;
        }
    }

    /**
     * Saves tasks and returns whether this action is successful.
     *
     * @return boolean The boolean indicating if save was successful.
     */
    private boolean hasSavedSuccessfully(TaskList tasks, Storage storage) {
        try {
            storage.saveData(tasks);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getWelcomeResponse() {
        String startingMessage = Ui.welcomeResponse;
        String response = Ui.addDoubleLineBreak(startingMessage, Ui.instructionResponse);
        return response;
    }

    public String getPreviousTaskResponse() {
        String loadMessage = hasLoadedSuccessfully() ? Ui.successfulLoadResponse : Ui.unsuccessfulLoadResponse;
        String response = Ui.addDoubleLineBreak(loadMessage, Ui.listTaskResponse(this.tasks));
        return response;
    }

    public String getAskForTaskResponse() {
        String askForTask = Ui.askForTaskResponse;
        return askForTask;
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
            String loadMessage = hasLoadedSuccessfully() ? "" : Ui.unsuccessfulLoadResponse;
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            isEnd = c.isExit();
            String response = c.execute(tasks, storage);
            String saveMessage = hasSavedSuccessfully(tasks, storage) ? "" : Ui.unsuccessfulSaveResponse;
            return Ui.addLineBreak(loadMessage, response, saveMessage, Ui.askForMoreTaskResponse);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
