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
     * initialises Ui, storage and tasklist
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

    /**
     * executes the command that is taken in as input from user
     *
     * @param input string input from user
     * @return response Duke will provide as a string
     */
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
