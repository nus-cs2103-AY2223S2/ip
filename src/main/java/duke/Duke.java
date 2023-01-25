package duke;

/**
 * Entry point of the application.
 * Initialize the program and interact with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to create duke instance.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            Storage.setDefaultStorage();
            tasks = new TaskList();
        }
    }

    /**
     * Start running the application.
     */
    public void run() {
        boolean isExit = false;

        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                String fullCommand = ui.requestForUserInput();
                //ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}