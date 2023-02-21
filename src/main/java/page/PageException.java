package page;

/**
 * Represents an Exception exclusive to the Page chatbot.
 */
public class PageException extends Exception {
    public PageException(String errorMessage) {
        super(errorMessage);
    }
}
