import java.io.IOException;

import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

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
    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * The program runs the Duke file with the correct file path.
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the Duke program.
     * @throws IOException Throws if there is an I/O error.
     */
    public void run() throws IOException {
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
            }
            ui.showLine();
        }
    }
}
