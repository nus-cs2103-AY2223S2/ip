public class DukeBadInstructionFormatException extends DukeException {

    /**
     * Constructor for a DukeBadInstructionFormatException.
     * @param message The error message.
     */
    public DukeBadInstructionFormatException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of a DukeBadInstructionFormatException.
     *
     * @return The string representation of a DukeBadInstructionFormatException.
     */
    @Override
    public String toString() {
        return "\t☹ OOPS!!! " + this.message;
    }
}
