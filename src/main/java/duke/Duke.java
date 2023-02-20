package duke;

import duke.GUI.GUI;
import duke.dukeExcpetion.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Platform;
import duke.command.Command;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@code Duke} class that encapsulates Duke program
 */
public class Duke {
    /**
     * filePath of directory to be accessed
     */
    private final Path DIRECTORY;

    /**
     * filePath of file to be accessed or edited
     */
    private final Path FILE;

    /**
     * stores a list of tasks to complete
     */
    private static TaskList taskList;

    /**
     * stores data of file specified by path
     */
    public static Storage storage;

    /**
     * handles gui of Duke Application
     */
    private GUI gui;

    /**
     * String name of directory
     */
    private final String PATH_DIRECTORY = "data";

    /**
     * String name of file
     */
    private final String PATH_FILE = "Duke.txt";

    /**
     * Constructor method of {@code Duke} class
     * @param filePath path of file to be accessed or edited
     */
    public Duke(String filePath, GUI gui) {
        this.gui = gui;
        DIRECTORY = Paths.get(filePath, PATH_DIRECTORY);
        FILE = Paths.get(filePath, PATH_DIRECTORY, PATH_FILE);
        try {
            if (!Files.exists(DIRECTORY)) {
                Files.createDirectory(DIRECTORY);
            }

            if (!Files.exists(FILE)) {
                Files.createFile(FILE);
            }

            storage = new Storage(FILE);
            taskList = new TaskList(storage.loadLines());
        } catch (InvalidPathException err) {
            gui.showLoadingError();
        } catch (IOException errIO) {
            System.out.println("Unable to create File!");
            Platform.exit();
            System.exit(-1);
        } catch (DukeException dukeErr) {
            gui.displayError(dukeErr);
        }
    }

    /**
     * Returns response to user's input
     * @param input user's input
     * @return output for duke
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            Command cmdHandler = parser.parseArgs();
            String output = cmdHandler.execArgs(taskList);

            storage.editFile(taskList.loadTaskList());
            return output;
        } catch (DukeException err) {
            String errMsg = GUI.BORDERLINE
                    + err.getErrorMessage().trim() + "\n"
                    + GUI.BORDERLINE;
            return errMsg;
        }
    }
}
