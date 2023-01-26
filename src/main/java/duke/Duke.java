package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a personal assistant chat bot that help to keep track of various stuff.
 */
public class Duke {
    // Attribute
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of initial data file.
     * @throws DukeException if filePath is invalid.
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Runs Duke chat bot.
     *
     * @throws IOException Unexpected IOException
     */
    public void run() throws IOException {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                Command c = Parser.read(sc);
                c.execute(ui, taskList, storage);
                if (c.isExit()) {
                    return;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Driver function.
     *
     * @param args Command line arguments
     * @throws DukeException If filePath is invalid
     * @throws IOException Unexpected IOException
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke1 = new Duke("./data/list.txt");
        duke1.run();
    }
}
