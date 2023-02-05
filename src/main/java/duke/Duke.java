package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exception.DukeException;


/**
 * Duke is a chatbot that helps with task management.
 *
 * @author CHU YI TING
 * @version CS2103 AY22/23 Semester 2
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param directoryPath Path of the directory.
     * @param filePath Path of file containing list of tasks.
     */
    public Duke(String directoryPath, String filePath) {
        this.storage = new Storage(directoryPath, filePath);
        try {
            this.tasks = storage.load();
            this.ui = new Ui(tasks);
        } catch (FileNotFoundException | DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
            this.ui = new Ui(tasks);
        }
    }

    protected String getResponse(String input) {
        String response = "";
        try {
            response = ui.acceptCommand(input);
            storage.saveTasksInFile(tasks);
        } catch (DukeException | IOException e) {
            response = ui.showErrorMessage(e);
        }
        return response;
    }

}
