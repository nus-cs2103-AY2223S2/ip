package duke.exception.editcommandexceptions;

import duke.exception.DukeException;

/**
 * Thrown when no new details are specified for an edit command.
 */
public class NoNewDetailsException extends DukeException {
    /**
     * Thrown when no new details are specified for an edit command.
     */
    public NoNewDetailsException() {
        super("\n"
                + "Gonna need to specify what new details you want to change the details of the task to!\n"
                + "Do \"edit {task number} {new details of the task}\"");
    }
}
