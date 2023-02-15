package henz.command;

import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * ExitCommand class extends from Command class.
 */
public class ExitCommand extends Command {

    /**
     * Constructor.
     * @param input
     */
    public ExitCommand() {
        super(Commands.EXIT);
        super.toggleIsExit();
    }

    /**
     * Exits UI.
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return "Todeloo!";
    }
}
