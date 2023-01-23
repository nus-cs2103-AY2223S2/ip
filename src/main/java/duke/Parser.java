package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Returns the command (e.g. "bye", "mark", "event", etc.).
     *
     * @param str User input.
     * @return Command.
     */
    public String getCommand(String str) {
        String[] split = str.split(" ", 2);
        return split[0];
    }

    /**
     * Returns details (e.g. the index for a "mark" command, or the description for an "event" command).
     *
     * @param str User input.
     * @return Command details.
     */
    public String getCommandDetails(String str) {
        String[] split = str.split(" ", 2);
        return split[1];
    }

    /**
     * Returns description of a Deadline task.
     *
     * @param str User input.
     * @return Description of a deadline task.
     */
    public String getDeadlineDesc(String str) {
        String[] split = getCommandDetails(str).split(" /by ");
        return split[0];
    }

    /**
     * Returns deadline of a Deadline task.
     *
     * @param str User input.
     * @return Deadline of a deadline task.
     */
    public String getDeadline(String str) {
        String[] split = getCommandDetails(str).split(" /by ");
        return split[1];
    }

    /**
     * Returns description of an Event task.
     *
     * @param str User input.
     * @return Description of an Event task.
     */
    public String getEventDesc(String str) {
        String[] split = getCommandDetails(str).split(" /from ");
        return split[0];
    }

    /**
     * Returns "from" timeframe of an Event task.
     *
     * @param str User input.
     * @return "From" timeframe of an Event task.
     */
    public String getEventFrom(String str) {
        String[] split = getCommandDetails(str).split(" /from ");
        return split[1].split(" /to ")[0];
    }

    /**
     * Returns "to" timeframe of an Event task.
     *
     * @param str User input.
     * @return "To" timeframe of an Event task.
     */
    public String getEventTo(String str) {
        String[] split = getCommandDetails(str).split(" /from ");
        return split[1].split(" /to ")[1];
    }
}
