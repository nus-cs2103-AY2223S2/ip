package duke;

import duke.commands.Command;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Duke(getFilePath()).run();
    }

    private static String getFilePath() {
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
