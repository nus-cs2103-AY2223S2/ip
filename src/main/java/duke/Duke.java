package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Project duke.Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying as many
 * Java and SE techniques as possible along the way.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Duke {

    private Storage storage;

    private TaskList tasks;

    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showInitMessage();
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.makeSeperation();
                }
            }
        }
        ui.makeSeperation();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
