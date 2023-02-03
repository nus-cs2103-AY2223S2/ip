package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Project Duke is an educational software project designed to take you through
 * the steps of building a small software incrementally, while applying as many
 * Java and SE techniques as possible along the way.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Duke {
    /**
     * The <code>Storage</code> object Duke accesses.
     */
    private Storage storage;
    /**
     * The <code>TaskList</code> object Duke accesses.
     */
    private TaskList tasks;
    /**
     * The <code>Ui</code> object Duke accesses.
     */
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Filepath of the storage txt file
     */
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

    /**
     * Starts Duke's backend processes.
     */
    public void run() {
        ui.showInitMessage();
        ui.showGreeting();
        ui.makeSeperation();
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        String ret = "";
        System.out.println(input);
        try {
            Command c = Parser.parse(input);
            ret = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            System.out.println(ret);
            return ret;
        } catch (DukeException e) {
            System.out.println("error: " + e);
            ret = ui.showError(e.getMessage());
        }
        return ret;

    }

    //public static void main(String[] args) {
    //    new Duke("data/tasks.txt").run();
    //}

}
