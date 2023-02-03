package duke;

import java.io.IOException;

import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This program allows you to keep track of your upcoming To Dos, Deadlines and Events.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for the Duke file
     * @param filePath The file path given in String.
     * @throws IOException Throws if there is an I/O error.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * The program runs the Duke file with the correct file path.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the Duke program.
     * @throws IOException Throws if there is an I/O error.
     */
    public void run() {
        boolean isContinueConvo = true;
        while (isContinueConvo) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = new Parser().parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isContinueConvo = c.isContinueConvo();
            } catch (DukeException e) {
                ui.showError(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.showLine();
        }
    }
}
