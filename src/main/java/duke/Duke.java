package duke;

import duke.commands.Command;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

/**
 * This is the driver class for Duke, the CLI task manager.
 * Every Duke object has its own storage, tasklist, and ui objects.
 *
 * @author Jian Cheng
 * @version 0.1
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Duke object.
     * @param filePath the filepath of the serialized TaskList object stored in memory
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke object.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException |DateTimeParseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
    public static void main(String[] args) {
        new Duke(getFilePath()).run();
    }
     **/

    /**
     * Returns a system-specific string denoting the location of DukeMem.
     *
     * First, if it doesn't exist yet, the data directory containing DukeMem
     * will be created. Then, the path to the DukeMem.ser file in the
     * new directory will be generated and returned, regardless of whether
     * it exists yet.
     *
     * @return filePath system-specific string indicating the location of DukeMem
     */
    public static String getFilePath() {
        Path dirPath = Paths.get(".", "data");
        try {
            //This method creates a directory if it does not exist yet, but will not
            //throw an error even if it exists, and so is safe to call.
            Files.createDirectories(dirPath);
        } catch (IOException e){
            e.printStackTrace();
        }
        Path dataPath = Paths.get(dirPath.toString(), "DukeMem.ser");
        return dataPath.toString();
    }


}
