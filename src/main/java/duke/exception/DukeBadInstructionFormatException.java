package duke.exception;

/**
 * If the user inputs a <code>Command</code> of wrong format, then a
 * <code>DukeBadInstructionFormatException</code> will be thrown.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DukeBadInstructionFormatException extends DukeException {
    /**
     * Constructor for a <code>DukeBadInstructionFormatException</code>.
     * @param message The error message.
     */
    public DukeBadInstructionFormatException(String message) {
        super(message);
    }
    /**
     * Returns the string representation of a <code>DukeBadInstructionFormatException</code>.
     * @return The string representation of a <code>DukeBadInstructionFormatException.</code>.
     */
    @Override
    public String toString() {
        return "\t:( OOPS!!! " + this.message;
    }
}
