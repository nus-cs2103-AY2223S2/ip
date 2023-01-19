import java.util.function.Function;

/**
 * Class that stores all helper functions and uncustomizable constants statically.
 */
public final class Util {
    //Cached constructor functional objects
    public static final Function<String, UserTask> todoNew = TodoTask::new;
    public static final String noFound = "N/A";
    public static final Function<String, UserTask> ddlNew = DdlTask::of;
    public static final Function<String, UserTask> eventNew = EventTask::of;

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
     * */
    public static String usageIdxCmd(String cmd){
        return Resource.notifUsage+cmd+" <idx: integer between 1 and list size (inclusive)>";
    }

}
