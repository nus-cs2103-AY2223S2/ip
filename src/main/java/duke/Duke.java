package duke;

/**
 * Main class.
 */
public class Duke {

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private static final String filePath = "data/duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFile();
    }

    public String greeting() {
        return ui.dukeGreeting();
    }

    public String getResponse(String input) {
        String output = "";
        Parser parser = new Parser(taskList, ui, storage);
        output = parser.parse(input);
        return output;
    }
}

