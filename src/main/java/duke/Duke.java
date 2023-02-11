package duke;
import duke.command.Command;
import duke.exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.stage.Stage;
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
        String response;
        try {
            Command command = parser.parse(userInput);
            response = command.execute(this.taskList, this.ui);
            storage.saveToFile(this.taskList);
            if (command.isExit()) {
                PauseTransition wait = new PauseTransition(Duration.seconds(2));
                wait.setOnFinished(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                wait.play();
                return response;
            }
            return response;
        } catch (DukeException e) {
            response = ui.showError(e.toString());
            response += ui.showLine();
            return response;
        }
    }

    /**
     * Reads user input and loads data from file into an instance of Duke.
     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String userInput = this.ui.readCommand();
//                ui.showLine();
//                Command command = parser.parse(userInput);
//                command.execute(this.taskList, this.ui);
//                storage.saveToFile(this.taskList);
//                isExit = command.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.toString());
//                ui.showLine();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke(PATH).run();
//    }
}
