package babe.exception;

/**
 * This exception is thrown when the user inputs date format not as specified for deadline or event commands.
 */
public class WrongDateFormatException extends Exception{

    public WrongDateFormatException() {
        super("The date format should be yyyy-mm-dd, luv. Please try again.");
    }

}
