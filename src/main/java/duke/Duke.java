package duke;

import duke.commands.Command;
import javafx.application.Platform;

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

    public String getResponse(String input) throws TaskException {
        Command s = parser.parse(input);
        if (s.isExit()) {
            Platform.exit();
        }
        return s.execute(taskList, storage);
    }
}
