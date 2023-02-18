package duke;

/**
 * Class that contains the main method to run the bot.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke object with given file path.
     *
     * @param filePath String path to file location.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.readTasksFromFile(ui));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean canContinue = true;
        while (canContinue) {
            try {
                String userCommandText = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parse(userCommandText);
                c.execute(this.tasks, this.ui, this.storage);
                canContinue = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Given user input, parses it and gets response from
     * executing the associated Command. If no such command,
     * returns the error message instead.
     *
     * @param input User input message.
     * @return Response after executing command, or error message.
     */
    public String getResponse(String input) {
        try {
            String userCommandText = input;
            Command c = Parser.parse(userCommandText);
            assert c instanceof Command : "c should be a Command";
            return c.executeReturnString(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return ui.formatMessage(e.getMessage());
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke(System.getProperty("user.dir") + "/duke.txt").run();
    }

}
