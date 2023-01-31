package duke.exception.DatabaseExceptions;

import duke.exception.DukeException;

public class DatabaseNotUpdatingException extends DukeException {

    public DatabaseNotUpdatingException() {
        super("\n" + "    ____________________________________________________________\n" +
                "Oh dear, it seems something went wrong with the Database." + "\n" +
                "Try updating it again" + "\n" +
                "    ____________________________________________________________\n");
    }
}
