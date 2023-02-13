package duke.exception.databaseexceptions;

import duke.exception.DukeException;

/**
 * Thrown when one of the tasks stored in the database has the wrong type.
 */
public class DatabaseTypeNotFoundException extends DukeException {

    public DatabaseTypeNotFoundException() {
        super("\n" + "Oh no, it seems that one of the tasks stored in the database has a wrong type");
    }
}
