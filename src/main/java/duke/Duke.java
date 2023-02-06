package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class to run Duke programme.
 *
 * @author Lian Kok Hai
 *
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke instance with saved information loaded from save directory.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        try {
            storage.loadFromSave(taskList);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Runs the Duke functionality.
     */
    public void run() {
        Scanner s = new Scanner(System.in);
        String text;
        boolean isExit = false;

        ui.printWelcome();

        while (!isExit) {
            text = s.nextLine();
            try {
                Command c = parser.parse(text);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Get Duke's response.
     * @param input String input by user.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
