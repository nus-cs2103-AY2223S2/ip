package duke;

import duke.command.Command;

/**
 * Driver code for the Duke project.
 */
public class Duke {
    private static final String SAVE_PATH = "./tasklist.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke object.
     *
     * @param filePath Relative path to saved TaskList file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.loadTasklistFromFile());
        } catch (DukeException e) {
            // ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_PATH);
        duke.run();
    }

    /**
     * Runs the Duke instance and begins the command loop.
     */
    public void run() {
        this.ui.greet();
        commandLoop();
    }

    /**
     * Takes commands repeatedly and executes them until
     * "bye" is supplied.
     */
    public void commandLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                // ui.showError(e.getMessage());
            } finally {
                // ui.showLine();
            }
        }

        // printInBanner("Otsunakiri~", "Byebye!~");
    }
}
