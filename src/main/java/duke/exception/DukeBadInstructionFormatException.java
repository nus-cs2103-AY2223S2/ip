package duke.exception;

public class DukeBadInstructionFormatException extends DukeException {

    /**
     * Constructor for a duke.exception.DukeBadInstructionFormatException.
     * @param message The error message.
     */
    public DukeBadInstructionFormatException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of a duke.exception.DukeBadInstructionFormatException.
     *
     * @return The string representation of a duke.exception.DukeBadInstructionFormatException.
     */
    @Override
    public String toString() {
        return "\t☹ OOPS!!! " + this.message;
    }
}
