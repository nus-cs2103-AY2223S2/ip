package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.cli.Cli;
import duke.utils.DukeUtils;

/**
 * Main class of the command-line interface version of the app.
 */
public class DukeCli {
    private TaskList tasks;

    private DukeCli() {
        Parser parser = new Parser();

        Ui ui = new Cli(System.out, System.in, (input, printer) -> DukeUtils.handleInput(input, printer, parser, tasks),
                parser::isByeCommand);

        try {
            tasks = DukeUtils.loadTasks();
        } catch (DukeException e) {
            ui.print(e.getMessage());
            return;
        }

        ui.print(DukeUtils.getGreetingMessage());
        ui.start();
    }

    /**
     * Runs the app.
     */
    public static void run() {
        new DukeCli();
    }
}
