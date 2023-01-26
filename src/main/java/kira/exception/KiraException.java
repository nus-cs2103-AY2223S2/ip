package kira.exception;

public class KiraException extends Exception {
    
    /**
     * Constructs an exception with the message.
     * 
     * @param message
     */
    public KiraException(String message) {
        super(message);
    }

    public KiraException() {
        super();
    }
}