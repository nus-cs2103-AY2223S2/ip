package duke;

import java.util.function.Consumer;

import duke.command.Command;
import duke.database.DukeRepo;
import duke.database.DukeRepoImpl;
import duke.exception.DukeException;
import duke.parser.Parser;
import javafx.application.Platform;

/**
 * Duke agent that knows how to manage a todo list.
 */
public class Duke {

    private DukeRepo db;

    /**
     * Default constructor.
     */
    public Duke() {
        db = new DukeRepoImpl();
    }

    /**
     * Generates a response to user input.
     */
    public void getResponse(String input, Consumer<String> con) {
        try {
            Command c = Parser.parse(input);
            c.execute(db, con);
            if (c.isExit()) {
                close();
            }
        } catch (DukeException e) {
            con.accept(e.getMessage());
        }
    }

    /**
     * Handles exit event gracefully.
     */
    public void close() {
        db.close();
        Platform.exit();
    }
}
