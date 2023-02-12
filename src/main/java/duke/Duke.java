package duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/** The Duke class contains variables and methods related to running an instance of Duke. */
public class Duke {
    protected static final String PATH = "src/data/duke.txt";
    protected TaskList taskList;
    protected final Parser parser;
    protected final Ui ui;
    protected Storage storage;

    /**
     * Creates an instance of Duke.
     *
     * @param filePath path of the file containing data for Duke
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList();
            this.storage.loadFileInto(this.taskList);
        } catch (DukeException e) {
            ui.showError(e.toString());
        }
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            Command command = parser.parse(userInput);
            response = command.execute(this.taskList, this.ui);
            storage.saveToFile(this.taskList);
            if (command.isExit()) {
                //@@author wendy0107-reused
                //Reused from https://github.com/tyw2811/ip
                // with minor modifications.
                PauseTransition wait = new PauseTransition(Duration.seconds(2));
                wait.setOnFinished(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                wait.play();
            }
            assert !response.isEmpty() : "Error returning Luffy's response";
            return response;
        } catch (DukeException e) {
            response = ui.showError(e.toString());
            response += ui.showLine();
            return response;
        }
    }
}
