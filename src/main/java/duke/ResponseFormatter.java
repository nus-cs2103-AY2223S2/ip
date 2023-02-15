package duke;

/**
 * The ResponseFormatter class provides functions for formatting and styling a message as a response.
 */
public class ResponseFormatter implements IFormatter {
    /**
     * Formats a message as a response
     *
     * @param msg message to format
     * @return formatted message
     */
    @Override
    public String format(String msg) {
        return String.format("%s\n", msg);
    }
}
