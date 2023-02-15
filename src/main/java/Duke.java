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

    private boolean hasLoadedSuccessfully() {
        try {
            this.tasks = new TaskList(storage.loadData());
            return true;
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
            return false;
        }
    }

    private boolean hasSavedSuccessfully(TaskList tasks, Storage storage) {
        try {
            System.out.println(Ui.listTaskResponse(tasks));
            storage.saveData(tasks);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String getPreviousTaskResponse() {
        String startingMessage = Ui.welcomeResponse;
        String askForTask = Ui.askForTaskResponse;
        String loadMessage = hasLoadedSuccessfully() ? Ui.successfulLoadResponse : Ui.unsuccessfulLoadResponse;
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
            String loadMessage = hasLoadedSuccessfully() ? "" : Ui.unsuccessfulLoadResponse;
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            isEnd = c.isExit();
            String response = c.execute(tasks, storage);
            String saveMessage = hasSavedSuccessfully(tasks, storage) ? "" : Ui.unsuccessfulSaveResponse;
            return Ui.addLineBreak(loadMessage, response, saveMessage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
