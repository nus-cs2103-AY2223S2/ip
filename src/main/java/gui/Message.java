package gui;

/**
 * A message class that wraps a string
 * so that it can provide additional information to a string,
 * such as whether the string is an error message
 */
public class Message {
    protected String message;
    protected boolean isError;

    /**
     * Constructor
     * @param message the string message
     * @param isError whether this is an error message
     */
    public Message(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Returns a boolean indicating whether this is an error
     * @return a boolean indicating this is an error message or not
     */
    public boolean getIsError() {
        return isError;
    }

    @Override
    public String toString() {
        return message;
    }
}
