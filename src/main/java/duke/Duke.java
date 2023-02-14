package duke;

import java.util.function.BiConsumer;

import duke.command.Command;
import duke.constant.DialogType;
import duke.database.DukeRepo;
import duke.database.DukeRepoImpl;
import duke.exception.DukeException;
import duke.parser.Parser;
import javafx.application.Platform;

/**
 * A Duke agent that knows how to manage a todo list.
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
     *
     * @param input string command
     * @param con handler for outputs
     * @see BiConsumer
     */
    public void getResponse(String input, BiConsumer<DialogType, String> con) {
        try {
            Command c = Parser.parse(input);
            c.execute(db, con);
            if (c.isExit()) {
                close();
            }
        } catch (DukeException e) {
            con.accept(DialogType.ERROR, e.getMessage());
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
