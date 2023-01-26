package kira.exception;

/**
 * KiraException denotes all exception handled by the bot.
 */
public class KiraException extends Exception {
    
    /**
     * Constructs an exception related to the bot with a message.
     * 
     * @param msg
     */
    public KiraException(String msg) {
        super(msg);
    }

    /**
     * Constructs an exception related to the bot.
     */
    public KiraException() {
        super();
    }
}