package james.exception;


public class JamesException extends Exception {
    /**
     * Throws a JamesException, which is an exception that is thrown by James.
     */
    public JamesException(String message) {
        super(message);
    }

}
