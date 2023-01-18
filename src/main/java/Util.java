import java.util.function.Function;

/**
 * Class that stores all helper functions and uncustomizable constants statically.
 */
public final class Util {
    //Cached constructor functional objects
    public static final Function<String, UserTask> todoNew = TodoTask::new;
    public static final Function<String, UserTask> ddlNew = DdlTask::of;
    public static final Function<String, UserTask> eventNew = EventTask::of;
    public static final String noFound="N/A";
    /**
     * @deprecated Class that stores all resource values statically. Should not be initialized.
     */
    Util() {
    }

    public static String parenthesize(char c) {
        return "[" + c + ']';
    }
}
