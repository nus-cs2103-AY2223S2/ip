package luffy;
import luffy.command.Command;
import luffy.exception.LuffyException;
import luffy.storage.Storage;
import luffy.storage.TaskList;
import luffy.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/** The Luffy class contains variables and methods related to running an instance of Luffy. */
public class Luffy {
    protected static final String PATH = "src/data/luffy.txt";
    protected TaskList taskList;
    protected final Parser parser;
    protected final Ui ui;
    protected Storage storage;

    /**
     * Creates an instance of Luffy.
     *
     * @param filePath path of the file containing data for Luffy
     */
    public Luffy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList();
            this.storage.loadFileInto(this.taskList);
        } catch (LuffyException e) {
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
        } catch (LuffyException e) {
            response = ui.showError(e.toString());
            return response;
        }
    }
}
