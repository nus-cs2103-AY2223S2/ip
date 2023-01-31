package duke.exception.DatabaseExceptions;

import duke.exception.DukeException;

public class DatabaseNotLoadingException extends DukeException {

    public DatabaseNotLoadingException() {
        super("\n" + "    ____________________________________________________________\n" +
                "Oh dear, it seems something went wrong with the Database." + "\n" +
                "Creating an empty one for now" + "\n" +
                "    ____________________________________________________________\n");
    }
}
