package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.cli.Cli;
import duke.utils.DukeUtils;

import java.util.function.Consumer;

/**
 * Main class of the CLI version of the app.
 */
public class CliDuke {
    private Parser parser;
    private Ui ui;

    private boolean didSetupFail;

    private TaskList tasks;

    private CliDuke() {
        parser = new Parser();
        ui = (Ui) (new Cli(System.out, System.in, this::handleInput, parser::isByeCommand));

        try {
            tasks = DukeUtils.loadTasks();
        } catch (DukeException e) {
            ui.print(e.getMessage());
            didSetupFail = true;
        }
    }

    /**
     * Launches the CLI version of the app.
     */
    public static void launch() {
        CliDuke duke = new CliDuke();
        if (duke.didSetupFail) {
            return;
        }

        duke.run();
    }

    private void handleInput(String input, Consumer<String> printer) {
        DukeUtils.handleInput(input, tasks, parser, printer);
    }

    private void run() {
        ui.print(DukeUtils.getGreetingMessage());
        ui.start();
    }
}
