import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidFileFormatException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import java.nio.file.Path;

public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

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

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path dukeFilePath = Path.of(home, "duke.txt");
        new Duke(dukeFilePath.toString()).run();
    }

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
