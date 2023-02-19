package dukeexceptions;

/**
 * Encapsulates an Exception that may occur from the chatbot.
 */
class DukeException extends Exception {
    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}

