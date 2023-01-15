package duke;

import duke.utils.Ui;

import duke.command.Command;
import duke.database.DukeRepo;
import duke.database.DukeRepoImpl;
import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * Duke agent that knows how to manage a todo list.
 */
public class Duke {

    private DukeRepo db;
    private Ui ui;

    /**
     * Default constructor.
     */
    Duke() {
        // isActive = true;
        db = new DukeRepoImpl();
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(db, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
