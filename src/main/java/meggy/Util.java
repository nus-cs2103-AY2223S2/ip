package meggy;

import meggy.exception.Function;
import meggy.task.DdlTask;
import meggy.task.EventTask;
import meggy.task.TodoTask;
import meggy.task.UserTask;

/** Class that stores all helper functions and uncustomizable constants statically. */
public final class Util {
    /** Cached {@link TodoTask} constructor functional object. */
    public static final Function<String, UserTask> TODO_NEW = TodoTask::new;
    /** Cached {@link DdlTask} "constructor" functional object. */
    public static final Function<String, UserTask> DDL_NEW = DdlTask::of;
    /** Cached {@link EventTask} "constructor" functional object. */
    public static final Function<String, UserTask> EVENT_NEW = EventTask::of;
    /** Default time information if corresponding time keyword is absent in user input. */
    public static final String NO_FOUND = "N/A";
    /** Task list data file. */
    public static final String DATA_FILE_PATH = "MeggyData.txt";
    public static final String ERROR_WRONG_FILE_0 = "This file does NOT look like my task list record: \"";
    public static final String ERROR_WRONG_FILE_1 = "\". If this file is important, please back up before executing a" +
            "ny more command because this file will be overwritten!";

    /** @deprecated Class stores all values statically should not be initialized. */
    private Util() {
    }

    /** Puts {@link Character}s in square brackets. */
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
        assert cmd != null;
        return Resource.NOTIF_USAGE + cmd + " <idx: integer between 1 and list size (inclusive)>\n";
    }
}
