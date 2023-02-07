package duke.main;

import duke.command.Command;
import java.io.IOException;

/**
 * Main class for Duke program
 */
public class Duke {
    private final Ui UI;
    private Tasklist tasks;
    private Storage storage;
    private boolean shouldExit = false;

    /**
     * Duke constructor to initialise Ui, storage and tasklist
     */
    public Duke() {
        UI = new Ui();
        try {
            String FILEPATH = "data/duke.txt";
            storage = new Storage(FILEPATH);
            tasks = new Tasklist(storage.load());
        } catch (IOException ie) {
            UI.printException(ie);
            System.exit(0);
        } catch (DukeException de) {
            UI.printException(de);
            tasks = new Tasklist();
        }
    }

    public String getResponse(String input) {
        if (shouldExit) {
            return "";
        }
        try {
            Command command = Parser.parseCommand(input);
            shouldExit = command.isExit();
            return command.execute(tasks, UI, storage);
        } catch (DukeException | IOException e) {
            return UI.printException(e);
        }
    }
}
