package duke.application;

import java.nio.file.Path;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidFileFormatException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import javafx.application.Platform;

public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;
    private String initErrorMessage;

    /**
     * Instantiates a new Duke object.
     *
     * @param filePath The path to the {@code duke.txt} file. Duke will save the user's tasks
     *                 to {@code duke.txt} after every command, and load from it on startup.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeInvalidFileFormatException e) {
            taskList = new TaskList();
            initErrorMessage = e.getMessage();
        }
    }

    /**
     * Driver code for Duke.
     *
     * @param args The command line arguments supplied to Duke.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path dukeFilePath = Path.of(home, "duke.txt");
        Duke duke = new Duke(dukeFilePath.toString());

        if (duke.getInitErrorMessage() != null) {
            String errorMessage = duke.getInitErrorMessage();
            duke.ui.showMessage(errorMessage);
        }

        duke.run();
    }

    public String getInitErrorMessage() {
        return initErrorMessage;
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isByeCommand = false;

        while (!isByeCommand) {
            try {
                ui.showPrompt();
                String line = ui.readLine();
                Command command = Parser.parseUserCommand(line);
                command.execute(taskList, ui, storage);
                isByeCommand = command.isByeCommand();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String line) {
        try {
            Command command = Parser.parseUserCommand(line);
            String response = command.execute(taskList, ui, storage);

            if (command.isByeCommand()) {
                Platform.exit();
            }

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
