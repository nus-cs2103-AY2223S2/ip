package duke;

/**
 * Main class.
 */
public class Duke {

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private static final String filePath = "data/duke.txt";

    /**
     * Creates new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFile();
    }

    /**
     * Shows Duke greeting message.
     *
     * @return Duke greeting message.
     */
    public String greeting() {
        return Ui.dukeGreeting();
    }

    /**
     * Gets response from user input.
     *
     * @param input User input.
     * @return Response to user input.
     */
    public String getResponse(String input) {
        String output = "";
        Parser parser = new Parser(taskList, ui, storage);
        try {
            output = parser.parse(input);
        } catch (WrongTask | OutOfBounds | EmptyDescription e) {
            return ui.errorMessage(e);
        }
        return output;
    }
}

