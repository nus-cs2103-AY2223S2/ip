import java.nio.file.Path;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidFileFormatException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Instantiates a new Duke app.
     *
     * @param filePath The path to the {@code duke.txt} file. The Duke app will save the user's tasks
     *                 to {@code duke.txt} after every command, and load from it on startup.
     */
    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeInvalidFileFormatException e) {
            e.printStackTrace();
            ui.showMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Driver code for the Duke app.
     *
     * @param args The command line arguments supplied to the Duke app.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path dukeFilePath = Path.of(home, "duke.txt");
        new Duke(dukeFilePath.toString()).run();
    }

    /**
     * Runs the Duke app.
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
}
