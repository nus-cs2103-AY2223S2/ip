package Meggy;

import Meggy.Exception.MeggyException;
import Meggy.Task.DdlTask;
import Meggy.Task.EventTask;
import Meggy.Task.TodoTask;
import Meggy.Task.UserTask;

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
     * Meggy.Task list data file.
     */
    public static final String dataFilePath = "MeggyData.txt";

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
}
