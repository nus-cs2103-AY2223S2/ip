package duke.Exceptions;

/**
 * The class represents the MissingDescriptionException
 */
public class MissingDescriptionException extends Exception {
    /**
     * The constructor for MissingDescriptionException
     * @param e the error message
     */
    public MissingDescriptionException(String e) {
        super(String.format("    OOPS!!! The description of a " + e + " cannot be empty. " +
                "You better put something there."));
    }

    @Override
    public String getMessage() {
        return "    OOPS!!! The description cannot be empty. " +
                "You better put something there.";
    }
}
