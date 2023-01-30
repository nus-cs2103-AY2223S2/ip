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
 * Main class of the graphical user interface version of the app.
 */
public class DukeGui extends Application {
    private Parser parser;
    private Ui ui;

    private TaskList tasks;

    private boolean shouldExit;

    /**
     * Runs the app.
     *
     * @param args Command-line arguments.
     */
    public static void run(String[] args) {
        Application.launch(DukeGui.class, args);
    }

    @Override
    public void start(Stage stage) {
        parser = new Parser();

        ui = new Gui(stage, this::handleInput);
        ui.start();

        try {
            tasks = DukeUtils.loadTasks();
        } catch (DukeException e) {
            ui.print(e.getMessage());

            setupForExit();

            return;
        }

        ui.print(DukeUtils.getGreetingMessage());
    }

    private void handleInput(String input, Consumer<String> printer) {
        if (shouldExit) {
            Platform.exit();
        }

        DukeUtils.handleInput(input, printer, parser, tasks);

        if (parser.isByeCommand(input)) {
            setupForExit();
        }
    }

    private void setupForExit() {
        shouldExit = true;
        ui.print("Duke will exit on the next input ...");
    }
}
