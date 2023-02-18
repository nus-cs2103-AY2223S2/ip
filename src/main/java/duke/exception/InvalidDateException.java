package duke.exception;

/**
 * Thrown when an invalid date string is given for a date related command.
 */
public class InvalidDateException extends DukeException {

    /**
     * Thrown when an invalid date string is given for a date related command.
     */
    public InvalidDateException() {
        super("\n" + "I can't seem to understand that date..." + "\n"
                + "Can you use DD-MM-YYYY HH:MM if possible?");
    }

}
