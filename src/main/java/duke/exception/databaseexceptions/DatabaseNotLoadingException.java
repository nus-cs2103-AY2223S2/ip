package duke.exception.databaseexceptions;

import duke.exception.DukeException;

/**
 *  Thrown when the database does not load.
 */
public class DatabaseNotLoadingException extends DukeException {

    /**
     *  Thrown when the database does not load.
     */
    public DatabaseNotLoadingException() {
        super("\n" + "Oh dear, it seems something went wrong with the Database." + "\n"
                + "Creating an empty one for now" + "\n");
    }
}
