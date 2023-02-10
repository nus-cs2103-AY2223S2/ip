package duke;

import duke.command.Command;
import duke.command.RemindCommand;
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
     * Boolean to track whether we should close the Duke GUI.
     */
    private boolean isExit = false;
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
     * Finds and returns string representation of tasks needing reminders.
     * @return String representation of tasks needing reminders.
     */
    public String getInitReminders() {
        RemindCommand reminders = new RemindCommand(null);
        return reminders.execute(tasks, ui, storage);
    }
    /**
     * Returns a string of the response for the Duke GUI.
     * @param input User's input
     * @return Duke's response to input
     */
    public String getResponse(String input) {
        String ret = "";
        System.out.println(input);
        try {
            Command c = Parser.parse(input);
            ret = c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            System.out.println(ret);
            return ret;
        } catch (DukeException e) {
            System.out.println("error: " + e);
            ret = ui.showError(e.getMessage());
        }
        return ret;

    }

    /**
     * Returns true if the Duke program should be closing. False otherwise.
     * @return True if the Duke program should be closing. False otherwise.
     */
    public boolean checkIfExit() {
        return this.isExit;
    }

}
