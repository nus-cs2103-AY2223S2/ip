package meggy;

/** Class that stores all customizable data statically. Works like a 'resource pack' */
public final class Resource {
    /** Prefix of all error messages. */
    public static final String ERR_BASE = "OOPS!!! ";
    /** Error message if no argument is provided to a command that needs argument(s). */
    public static final String ERR_NO_ARGS = "Description of this command cannot be empty.\n";
    /** Error message if an error prevents writing to a file. */
    public static final String ERR_FILE_WRITE = "Can't save data to file. ";
    /** Error message if an {@link java.io.IOException} prevents file interaction. */
    public static final String ERR_IO = "An IO error occurred.\n";
    /** Error message if an {@link SecurityException} prevents file interaction. */
    public static final String ERR_NO_FILE_ACCESS = "File access denied by system.\n";
    /** Error message if a task to be inserted already have an equivalent existing in task list. */
    public static final String ERR_DUPE_TASK = "Task already exist in list:\n";
    /** Indentation before task string when displaying in list. */
    public static final String TASK_STRING_INDENT = "    ";
    /** 'Exit' command. */
    public static final String CMD_EXIT = "bye";
    /** 'List' command. */
    public static final String CMD_LIST = "list";
    /** 'Mark' command. */
    public static final String CMD_MARK = "mark";
    /** 'Unmark' command. */
    public static final String CMD_UNMK = "unmark";
    /** 'Todo' command. */
    public static final String CMD_TODO = "todo";
    /** 'Deadline' command. */
    public static final String CMD_DDL = "deadline";
    /** 'Event' command. */
    public static final String CMD_EVENT = "event";
    /** 'Delete' command. */
    public static final String CMD_DEL = "delete";
    /** 'Find' command. */
    public static final String CMD_FIND = "find";
    /** 'Due time' keyword of 'deadline' command. */
    public static final String KW_DUE = "by";
    /** 'Start time' keyword of 'event' command. */
    public static final String KW_STT = "from";
    /** 'End time' keyword of 'event' command. */
    public static final String KW_END = "to";
    /** Notification message after 'todo/deadline/event' command. */
    public static final String NOTIF_ADD = "Got it. Added this task:\n";
    /** Notification message after 'list' command. */
    public static final String NOTIF_LIST = "Here are the tasks in your list:\n";
    /** Notification message after 'mark' command. */
    public static final String NOTIF_MARK = "Booyah! Marked this task as done:\n";
    /** Notification message after 'unmark' command. */
    public static final String NOTIF_UNMK = "OK. Marked this task as not done:\n";
    /** Notification message after syntax error. */
    public static final String NOTIF_USAGE = "Correct usage: ";
    /** Notification message after 'delete' command. */
    public static final String NOTIF_DEL = "OK. Removed this task:\n";
    /** Notification message after 'find' command. */
    public static final String NOTIF_FIND = "Here are the matching tasks in your list:\n";
    /** Notification message after 'bye' command. */
    public static final String FAREWELL = "OK gotta go play more Turf Wars. Have a fresh day!\n";
    /** Icon for tasks marked 'done'. */
    public static final char DONE_MK = 'X';
    /** Front page greetings. */
    public static final String GREETINGS = "Wommy! Get REKT by the upcoming star of Inkopolis, Meggy!\n";

    /** @deprecated Class stores all resource values statically should not be initialized. */
    private Resource() {
    }

    /**
     * Formats an array/list index number.
     *
     * @return Formatted index (starts with 1).
     */
    public static String fmtIdx(int i) {
        return " " + (i + 1) + '.';
    }

    /**
     * Formats the notification message after each change to task list.
     *
     * @return Message about task list size.
     */
    public static String fmtTaskCnt(int nTask) {
        return "Now you have " + nTask + " task" + (nTask > 1 ? "s" : "") + " in the list.\n";
    }

    /**
     * Formats the message of error caused by {@link NumberFormatException}
     *
     * @param arg Non-null. String in question.
     * @return Error message.
     */
    public static String fmtErrNfe(String arg) {
        assert arg != null;
        return "Can't interpret \"" + arg + "\" as an index number.\n";
    }

    /**
     * Formats the notification message after empty input or unknown command or empty input.
     *
     * @param cmd Non-null. String in question.
     * @return Message about empty input or unknown command or empty input.
     */
    public static String fmtErrUnknownCmd(String cmd) {
        assert cmd != null;
        return cmd.isEmpty() ? "Umm that's all blank? Say something! Speak to me!\n" : "Don't know what \""
                + cmd + "\" means.\n";
    }

    /**
     * Formats the message of error would have caused by {@link IndexOutOfBoundsException}
     *
     * @param idx      Index (starts with 0) in question.
     * @param listSize Size of list that would cause {@link IndexOutOfBoundsException} when queried with idx.
     * @return Error message.
     */
    public static String fmtErrOutOfBounds(int idx, int listSize) {
        idx += 1;
        return "Can't retrieve item with " + (
                idx > 0 ? "index " + idx + " from a list of size " + listSize : "non-positive index (" + idx + ')')
                + ".\n";
    }
}
