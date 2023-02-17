package duke.exception;

import duke.Parser;

/**
 * A DukeException that deals with incomplete command entries.
 */
public class IncompleteCommandException extends DukeException {
    /**
     * Constructor for IncompleteCommandException.
     */
    public IncompleteCommandException(String command) {
        super(String.format("Invalid command! The correct format is:\n\t%s", Parser.COMMAND_FORMATS.get(command)));
    }
}
