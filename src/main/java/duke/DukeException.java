package duke;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception class, thrown by duke.Duke
 */
public class DukeException extends Exception {
    protected static final String ENCOURAGING_WORDS = "Can you try again? :D";
    protected String errorMessage;

    /**
     * Constructor.
     *
     * @param errorMessage the error message of the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor that prints out stack trace.
     * @param exception the exception
     */
    public DukeException(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        this.errorMessage = sw.toString();
    }

    /**
     * Wrap an error message with encouraging words
     * so that it feels better
     * @param errorMessage the original error message
     * @return the modified error message
     */
    protected static String wrapWithEncouragingWords(String errorMessage) {
        return errorMessage + "\n" + ENCOURAGING_WORDS;
    }

    /**
     * Returns the error message as a string
     *
     * @return the string error message
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
