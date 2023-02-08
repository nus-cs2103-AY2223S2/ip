package roody.exceptions;

/**
 * A custom exception for incorrect inputs
 */
public class InputFormatException extends RoodyException {
    public InputFormatException(String s) {
        super(s);
    }
}
