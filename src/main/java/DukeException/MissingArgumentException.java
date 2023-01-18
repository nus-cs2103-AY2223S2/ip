package DukeException;

import Storage.TaskList;
import Task.Todo;

public class MissingArgumentException extends DukeException {

    /**
     * Constructor to create an exception for Missing argument
     * @param message error message for the missing argument exception
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
