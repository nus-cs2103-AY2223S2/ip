package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.gui.Gui;
import duke.utils.DukeUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.function.Consumer;

/**
 * Main class of the GUI version of the app.
 */
public class GuiDuke extends Application {
    private Parser parser;
    private Ui ui;

    private boolean didSetupFail;

    private TaskList tasks;

    /**
     * Launches the GUI version of the app.
     *
     * @param args Command-line arguments.
     */
    public static void launch(String[] args) {
        Application.launch(GuiDuke.class, args);
    }

    @Override
    public void start(Stage stage) {
        setup(stage);
        run();
    }

    private void setup(Stage stage) {
        parser = new Parser();
        ui = (Ui) (new Gui(stage, this::handleInput));

        try {
            tasks = DukeUtils.loadTasks();
        } catch (DukeException e) {
            handleSetupFailure(e.getMessage());
        }
    }

    private void handleInput(String input, Consumer<String> printer) {
        if (shouldExit(input)) {
            Platform.exit();
            return;
        }

        DukeUtils.handleInput(input, tasks, parser, printer);
    }

    private boolean shouldExit(String input) {
        return didSetupFail || parser.isByeCommand(input);
    }

    private void handleSetupFailure(String errorMessage) {
        didSetupFail = true;
        ui.print(errorMessage);
        ui.print("Duke will exit on the next input ...");
    }

    private void run() {
        if (!didSetupFail) {
            ui.print(DukeUtils.getGreetingMessage());
        }

        ui.start();
    }
}
