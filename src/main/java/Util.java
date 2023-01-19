/**
 * Class that stores all helper functions and uncustomizable constants statically.
 */
public final class Util {
    /**
     * Cached {@link TodoTask} constructor functional object.
     */
    public static final MeggyException.Function<String, UserTask> todoNew = TodoTask::new;
    /**
     * Cached {@link DdlTask} "constructor" functional object.
     */
    public static final MeggyException.Function<String, UserTask> ddlNew = DdlTask::of;
    /**
     * Cached {@link EventTask} "constructor" functional object.
     */
    public static final MeggyException.Function<String, UserTask> eventNew = EventTask::of;
    /**
     * Default time information if corresponding time keyword is absent in user input.
     */
    public static final String noFound = "N/A";

    /**
     * @deprecated Class stores all resource values statically and should not be initialized.
     */
    Util() {
    }

    /**
     * Puts {@link Character}s in square brackets.
     */
    public static String parenthesize(char c) {
        return "[" + c + ']';
    }

    /**
     * Notification message to indicate the correct syntax of index commands (todo/deadline/event/delete)
     *
     * @param cmd Non-null. Index command. Currently 'mark', 'unmark', or 'delete' only.
     * @return Notification message.
     */
    public static String usageIdxCmd(String cmd) {
        return Resource.notifUsage + cmd + " <idx: integer between 1 and list size (inclusive)>\n";
    }

    /**
     * @param args Non-null. Trimmed arguments string.
     * @return The substring before the 1st whitespace character, or original string if no whitespace exists.
     */
    public static String get1stArg(String args) {
        final int whiteSpaceIdx = args.indexOf(' ');
        return whiteSpaceIdx < 0 ? args : args.substring(0, whiteSpaceIdx);
    }
}
