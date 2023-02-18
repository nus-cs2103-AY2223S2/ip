package lulu.exception;

/**
 * The general exception to be thrown for the chatbot, Lulu.
 */
public class LuluException extends Exception {
    public LuluException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
