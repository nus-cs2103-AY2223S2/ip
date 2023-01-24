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
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.loadTaskList());
        } catch (DukeInvalidFileFormatException e) {
            e.printStackTrace();
            this.ui.showMessage(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path dukeFilePath = Path.of(home, "duke.txt");
        new Duke(dukeFilePath.toString()).run();
    }

    public void run() {
        this.ui.showWelcomeMessage();
        boolean isByeCommand = false;

        while (!isByeCommand) {
            try {
                this.ui.showPrompt();
                String line = this.ui.readLine();
                Command command = Parser.parseUserCommand(line);
                command.execute(this.taskList, this.ui, this.storage);
                isByeCommand = command.isByeCommand();
            } catch (DukeException e) {
                this.ui.showMessage(e.getMessage());
            }
        }
    }
}
