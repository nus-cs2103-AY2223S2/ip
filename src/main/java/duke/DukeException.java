package duke;

/**
 * The exception class that handles exceptions when the bot cannot read the command.
 */
public class DukeException extends Exception {
    public DukeException(String str) {
        super(str);
    }
}
