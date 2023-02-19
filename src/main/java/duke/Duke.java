package duke;

/**
 * Class encapsulating the entire Duke application.
 */
public class Duke {
    protected static final String DATA_DIR = "data/";
    protected static final String DATA_FILENAME = "duke.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    public Duke() {
        this(DATA_DIR + DATA_FILENAME);
    }

    /**
     * Returns a Duke object.
     *
     * @param filePath The file path where the data file is located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
        assert tasks != null : "tasks should not be null";
    }

    /**
     * Returns a response, given a command.
     *
     * @param command Command given by the user.
     * @return Response from the bot.
     */
    public String getResponse(String command) {
        String trimmedCommand = command.trim();
        String response;
        try {
            response = parser.parseString(trimmedCommand, tasks, ui);
            storage.save(tasks);
        } catch (BadCommandException | DukeException e) {
            response = ui.showErrorMessage(e.getMessage());
        }
        return response;
    }
}
