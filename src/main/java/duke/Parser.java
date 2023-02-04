package duke;

/**
 * Parses String and returns type of info that is required for input
 */
public class Parser {
    public static String getFirstWord(String command) {
        return command.split(" ", 2)[0];
    }

    public static int getIndex(String command) {
        return Integer.parseInt(command.split(" ", 2)[1]) - 1;
    }

    /**
     * Gets desription of a todo task
     * @param command full command
     * @return description of todo task
     */
    public static String getDescription(String command) {
        return command.split(" ", 2)[1];
    }

    /**
     * Parses string of deadline task
     * @param command full command
     * @return an array with description + deadline
     */
    public static String[] parseDeadline(String command) {
        return command.split(" ", 2)[1].split(" /by ");
    }

    /**
     * Parses string of Event task
     * @param command full command
     * @return an array with description, start time and end time
     */
    public static String[] parseEvent(String command) {
        return command.split(" ", 2)[1].split("/from | /to ");
    }

    /**
     * Parse a find query
     * @param command the entire find query
     * @return the words that user wants to find
     */
    public static String parseQuery(String command) {
        return command.split(" ", 2)[1];
    }

}
