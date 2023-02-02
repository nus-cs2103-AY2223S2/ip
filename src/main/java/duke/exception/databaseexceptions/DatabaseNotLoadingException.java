package duke.exception.databaseexceptions;

import duke.exception.DukeException;

public class DatabaseNotLoadingException extends DukeException {

    public DatabaseNotLoadingException() {
        super("\n" + "Oh dear, it seems something went wrong with the Database." + "\n"
                + "Creating an empty one for now" + "\n");
    }
}
