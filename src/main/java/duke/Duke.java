package duke;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for the chatbot.
 */
public class Duke {
    /** Deals with loading tasks from the file and saving tasks in the file */
    private final Storage storage;

    /** Contains the task list */
    private TaskList tasks;

    /** Deals with interactions with the user */
    private final Ui ui;

    /**
     * Initializes the Ui, Storage and TaskList classes.
     * TaskList is initialized by loading the tasks
     * from the task file using the Storage.
     * If the task file does not exist, it will print
     * the error and create the task file.
     *
     * @param foldPath Path of the folder.
     * @param filePath Path of the file.
     */
    public Duke(Path foldPath, Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath.toString());
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            createFile(foldPath, filePath);
            tasks = new TaskList();
        }
    }

    /**
     * Creates the folder and file to save and
     * load tasks if they do not already exist.
     *
     * @param foldPath Path of the folder.
     * @param filePath Path of the file.
     */
    public void createFile(Path foldPath, Path filePath) {
        try {
            if (!Files.isDirectory(foldPath)) {
                Files.createDirectory(foldPath);
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            ui.showFileError();
        }
    }

    /**
     * Runs the chatbot by first printing the
     * welcome message and then reading the
     * commands input by the user. The commands
     * read will then be parsed and executed.
     */
    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Parser.parse(fullCommand, ui, tasks, storage);
            isExit = ui.getIsExit();
        }
    }

    /**
     * Initializes the Duke class and calls
     * the run function.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws DukeException {
        String fileSep = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        Path foldPath = Paths.get( userDir + fileSep + "data");
        Path filePath = Paths.get(foldPath + fileSep + "duke.txt");
        new Duke(foldPath, filePath).run();
    }
}
