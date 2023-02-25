package duke;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Class representing an instance of Duke.
 */
public class Duke {
    private static final int PAUSE_DURATION = 500;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Default constructor to initialize Duke
     */
    public Duke(String filePath, Ui ui) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
            this.ui = ui;
            this.parser = new Parser(tasks, ui);
        } catch (DukeException e) {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList();
            this.ui = ui;
            this.parser = new Parser(tasks, ui);
        }
    }

    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                storage.store(tasks);
                exitApp();
                return ui.getByeMessage();
            } else {
                return parser.parse(input);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void exitApp() {
        PauseTransition exitPause = new PauseTransition(Duration.millis(PAUSE_DURATION));
        exitPause.setOnFinished(event -> Platform.exit());
        exitPause.play();
    }
}
