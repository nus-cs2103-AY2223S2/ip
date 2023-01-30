package duke;

/**
 * Controller for Duke
 */
public class Duke {
    static final String SAVE_FOLDER = "data/save.txt";
    final Storage storage;
    final TaskList taskList;
    final Parser parser;

    Duke() {
        storage = new Storage(SAVE_FOLDER);
        taskList = storage.load();
        parser = new Parser();
    }

    public String getResponse(String input) {
        String s = parser.parse(taskList, input);
        storage.store(taskList);
        return s;
    }
}
