package duke;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception class, thrown by duke.Duke
 */
public class DukeException extends Exception {
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
        String exceptionAsString = sw.toString();
        this.errorMessage = exceptionAsString;
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
