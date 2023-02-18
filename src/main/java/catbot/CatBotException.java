package catbot;

/**
 * Custom exception class used whenever an error message needs to be communicated to the user.
 */
public class CatBotException extends Exception {

    /**
     * Initialises a CatBotException.
     * @param message is the message to show the user
     */
    public CatBotException(String message) {
        super(message + " >@w@<");
    }
}
