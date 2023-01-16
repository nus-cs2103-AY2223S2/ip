package duke;

import duke.command.Command;
import duke.database.DukeRepo;
import duke.database.DukeRepoImpl;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

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
        db = new DukeRepoImpl();
        ui = new Ui();
    }

    /**
     * Starts an interative console session with duke agent.
     */
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
