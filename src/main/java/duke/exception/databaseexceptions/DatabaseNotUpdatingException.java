package duke.exception.databaseexceptions;

import duke.exception.DukeException;

/**
 * Thrown when the database is not updated properly.
 */
public class DatabaseNotUpdatingException extends DukeException {

    /**
     * Thrown when the database is not updated properly.
     */
    public DatabaseNotUpdatingException() {
        super("\n" + "    ____________________________________________________________\n"
                + "Oh dear, it seems something went wrong with the Database." + "\n"
                + "Try updating it again" + "\n");
    }
}
