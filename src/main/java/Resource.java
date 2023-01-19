/**
 * Class that stores all customizable data statically. Works like a 'resource pack'.
 */
public final class Resource {
    /**
     * Prefix of all error messages.
     */
    public final static String errBase = " ☹ OOPS!!! ";
    /**
     * Error message if no argument is provided to a command that needs argument(s).
     */
    public final static String errNoArgs = "Description of this command cannot be empty.\n";
    /**
     * Indentation before task that are highlighted.
     */
    public final static String taskIndent = "    ";
    /**
     * 'Exit' command.
     */
    public final static String cmdExit = "bye";
    /**
     * 'List' command.
     */
    public final static String cmdList = "list";
    /**
     * 'Mark' command.
     */
    public final static String cmdMk = "mark";
    /**
     * 'Unmark' command.
     */
    public final static String cmdUnmk = "unmark";
    /**
     * 'Todo' command.
     */
    public final static String cmdTodo = "todo";
    /**
     * 'Deadline' command.
     */
    public final static String cmdDdl = "deadline";
    /**
     * 'Event' command.
     */
    public final static String cmdEvent = "event";
    /**
     * 'Delete' command.
     */
    public final static String cmdDel = "delete";
    /**
     * 'Due time' keyword of 'deadline' command.
     */
    public final static String kwDue = "by";
    /**
     * 'Start time' keyword of 'event' command.
     */
    public final static String kwStt = "from";
    /**
     * 'End time' keyword of 'event' command.
     */
    public final static String kwEnd = "to";
    /**
     * Notification message after 'todo/deadline/event' command.
     */
    public final static String notifAdd = " Got it. Added this task:\n";
    /**
     * Notification message after 'list' command.
     */
    public final static String notifList = " Here are the tasks in your list:\n";
    /**
     * Notification message after 'mark' command.
     */
    public final static String notifMk = " Booyah! Marked this task as done:\n";
    /**
     * Notification message after 'unmark' command.
     */
    public final static String notifUnmk = " OK. Marked this task as not done:\n";
    /**
     * Notification message after syntax error.
     */
    public final static String notifUsage = " Usage: ";
    /**
     * Notification message after 'delete' command.
     */
    public final static String notifDel = " OK. Removed this task:\n";
    /**
     * Front page wallpaper.
     */
    public final static String logo = " __  __\n|  \\/  |\n| \\  / | ___  __ _  __ _ _   _\n| |\\/| |/ _ \\/ _` |/ _`" +
            " | | | |\n| |  | |  __/ (_| | (_| | |_| |\n|_|  |_|\\___|\\__, |\\__, |\\__, |\n              __/ | __/ " +
            "| __/ |\n             |___/ |___/ |___/\n";
    /**
     * Message header.
     */
    public final static String msgHd = "------------------------------------------------------------\n";
    /**
     * Message tail.
     */

    public final static String msgTl = msgHd + "\n>";
    /**
     * Front page greetings.
     */
    public final static String greetings = " Wommy! Get REKT by the upcoming star of Inkopolis, Meggy!\n";
    /**
     * Notification message after 'bye' command.
     */
    public final static String farewell = " OK gotta go play more Turf Wars. Have a nice day!\n";
    /**
     * Icon for tasks marked 'done'.
     */
    public final static char doneMk = 'X';

    /**
     * @deprecated Class stores all resource values statically and should not be initialized.
     */
    Resource() {
    }

    /**
     * Format of array/list index number.
     *
     * @return Formatted index (start with 1).
     */
    public static String idxFmt(int i) {
        return " " + (i + 1) + '.';
    }

    /**
     * Notification message after each change to task list.
     *
     * @return Message about task list size.
     */
    public static String nTaskFmt(int nTask) {
        return " Now you have " + nTask + " task(s) in the list.\n";
    }

    /**
     * Msg of error caused by {@link NumberFormatException}
     *
     * @param arg Non-null. String in question.
     * @return Error message.
     */
    public static String errNFE(String arg) {
        return "Can not interpret \"" + arg + "\" as an index number.\n";
    }

    /**
     * Notification message after empty input or unknown command or empty input.
     *
     * @param cmd Non-null. String in question.
     * @return Message about empty input or unknown command or empty input.
     */
    public static String errUnknownCmd(String cmd) {
        return "".equals(cmd) ? "Umm that's all white space? Say something! Speak to me!\n" : "Don't know what \"" +
                cmd + "\" means.\n";
    }

    /**
     * Msg of error would have caused by {@link IndexOutOfBoundsException}
     *
     * @param idx      Index (starts with 0) in question.
     * @param listSize Size of list that would cause {@link IndexOutOfBoundsException} when queried with idx.
     * @return Error message.
     */
    public static String errOutOfBounds(int idx, int listSize) {
        idx += 1;
        return "Can not retrieve item with " +
                (idx > 0 ? "index " + idx + " from a list of size " + listSize : "non-positive index (" + idx + ')')
                + ".\n";
    }
}
