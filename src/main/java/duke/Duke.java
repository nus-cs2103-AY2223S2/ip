package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Runs the Duke process.
 */
public class Duke {
    private Storage storage = new Storage(System.getProperty("user.dir") +"/data/duke.txt");
    private TaskList tasks = new TaskList(storage.load());
    private Ui ui = new Ui();
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Generates response from the user's input.
     */
    String getResponse(String input) throws DukeException {
        try {
            return Ui.handleCommand(input, tasks);
        } catch(DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the data for an instance of Duke.
     */
    public void saveData() {
        storage.saveData(tasks);
        PauseTransition termination = new PauseTransition(Duration.seconds(1d));
        termination.setOnFinished(event -> Platform.exit());
        termination.play();
    }

}


