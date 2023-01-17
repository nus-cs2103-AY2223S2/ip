package exception;

/**
 * Exception to handle the case where the user input is ""
 */
public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String err) {
        super(err);
    }

    /**
     * Customised string for the error message
     * @return error message
     */
    @Override
    public String toString() {
        return "Oh no! " + super.getMessage();
    }
}
