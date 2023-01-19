/**
 * Class that stores all helper functions and uncustomizable constants statically.
 */
public final class Util {
    //Cached constructor functional objects
    public static final MeggyException.Function<String, UserTask> todoNew = TodoTask::new;
    /**
     * What to display if time information is absent in user input
     */
    public static final String noFound = "N/A";
    public static final MeggyException.Function<String, UserTask> ddlNew = DdlTask::of;
    public static final MeggyException.Function<String, UserTask> eventNew = EventTask::of;

    /**
     * @deprecated Class that stores all resource values statically. Should not be initialized.
     */
    Util() {
    }

    public static String parenthesize(char c) {
        return "[" + c + ']';
    }

    /**
     * @param cmd Index command. Currently 'mark', 'unmark', or 'delete' only.
     */
    public static String usageIdxCmd(String cmd) {
        return Resource.notifUsage + cmd + " <idx: integer between 1 and list size (inclusive)>\n";
    }

    /**
     * @param args Trimmed arguments string.
     */
    public static String get1stArg(String args) {
        final int whiteSpaceIdx = args.indexOf(' ');
        return whiteSpaceIdx < 0 ? args : args.substring(0, whiteSpaceIdx);
    }

}
