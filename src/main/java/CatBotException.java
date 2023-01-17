public class CatBotException extends Exception {
    /**
     * Custom exception class used whenever an error message needs to be
     * communicated to the user.
     * @param message is the message to show the user
     */
    public CatBotException(String message) {
        super(message);
    }
}
