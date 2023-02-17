package exceptions.invalid;

/**
 * Thrown for any generic invalid input.
 */
public class Input extends exceptions.DukeException {
    /**
     * This method constructs an Invalid Input Exception for the given input.
     * @param msg The message to be passed to Exception
     */
    public Input(String msg) {
        super(String.format("%s %s", OOPS, msg));
    }

}
