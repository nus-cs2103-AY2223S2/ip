package Duke.Exceptions;

public class MissingDescriptionException extends Exception {

    public MissingDescriptionException(String e) {
        super(String.format("☹ OOPS!!! The description of a " + e + " cannot be empty. " +
                "You better put something there."));

    }
}
