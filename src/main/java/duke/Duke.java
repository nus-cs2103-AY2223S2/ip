package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@code Duke} class that encapsulates Duke program
 */
public class Duke extends Application {
    /**
     * filePath of file to be accessed or edited
     */
    private Path path;

    /**
     * stores a list of tasks to complete
     */
    private TaskList taskList;

    /**
     * stores data of file specified by path
     */
    private Storage storage;

    /**
     * handles gui of Duke Application
     */
    private GUI gui= new GUI();

    /**
     * Default constructor to circumvent NoSuchMethodException problem when running
     * Application.launch
     *
     * Credit to @rmj1405 for providing the tip :)
     */
    public Duke() {}

    /**
     * Constructor method of {@code Duke} class
     * @param filePath path of file to be accessed or edited
     */
    public Duke(String filePath) {
        path = Paths.get(filePath, "data", "Duke.txt");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            storage = new Storage(path);
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
                    + err.errorMessage.trim() + "\n"
                    + GUI.BORDERLINE;
            return errMsg;
        }
    }

    /**
     * Starts Duke Application with default stage provided
     * @param stage default stage provided to start the application
     */
    @Override
    public void start(Stage stage) {
        gui.startUpProgram(stage);
        gui.runEvent();
    }
}
