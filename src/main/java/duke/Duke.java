package duke;

import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            this.tasks = storage.load();
        } catch (FileNotFoundException | DukeException e){
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke. Attempts to load stored tasks. Ends when user inputs "bye"
     * and saves tasks in a file.
     */
    public void run() {
        ui.showGreetingsMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                isExit = ui.acceptCommand(tasks);
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        try {
            storage.saveTasksInFile(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
        ui.showGoodbyeMessage();
    }

    /**
     * Initialise Duke chatbot.
     * @param args UNUSED
     */
    public static void main(String[] args) {
        new Duke("data", "data/tasks.txt").run();
    }

}
