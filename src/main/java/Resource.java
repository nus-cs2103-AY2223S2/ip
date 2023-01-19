/**
 * Class that stores all customizable resource values statically.
 */
public final class Resource {
    public final static String errBase = " ☹ OOPS!!! ";
    public final static String errNoArgs = "Description of this command cannot be empty.\n";
    public final static String taskIndent = "    ";
    public final static String cmdExit = "bye";
    public final static String cmdList = "list";
    public final static String cmdMk = "mark";
    public final static String cmdUnmk = "unmark";
    public final static String cmdTodo = "todo";
    public final static String cmdDdl = "deadline";
    public final static String cmdEvent = "event";
    public final static String cmdDel = "delete";
    public final static String kwDdl = "by";
    public final static String kwStt = "from";
    public final static String kwEnd = "to";
    public final static String notifAdd = " Got it. Added this task:\n";
    public final static String notifList = " Here are the tasks in your list:\n";
    public final static String notifMk = " Booyah! Marked this task as done:\n";
    public final static String notifUnmk = " OK. Marked this task as not done:\n";
    public final static String notifUsage = " Usage: ";
    public final static String notifDel = " OK. Removed this task:\n";
    public final static String logo = " __  __\n|  \\/  |\n| \\  / | ___  __ _  __ _ _   _\n| |\\/| |/ _ \\/ _` |/ _`" +
            " | | | |\n| |  | |  __/ (_| | (_| | |_| |\n|_|  |_|\\___|\\__, |\\__, |\\__, |\n              __/ | __/ " +
            "| __/ |\n             |___/ |___/ |___/\n";
    public final static String msgHd = "------------------------------------------------------------\n";
    public final static String msgTl = msgHd + "\n>";
    public final static String greetings = " Wommy! Get REKT by the upcoming star of Inkopolis, Meggy!\n";
    public final static String farewell = " OK gotta go play more Turf Wars. Have a nice day!\n";
    public final static char doneMk = 'X';

    /**
     * @deprecated Class that stores all resource values statically. Should not be initialized.
     */
    Resource() {
    }

    /**
     * Format to print array index number.
     */
    public static String idxFmt(int i) {
        return " " + (i + 1) + '.';
    }

    public static String statusFmt(boolean status) {
        return Util.parenthesize(status ? doneMk : ' ');
    }

    public static String nTaskFmt(int nTask) {
        return " Now you have " + nTask + " task(s) in the list.\n";
    }

    /**
     * Msg of error created by NumberFormatException
     */
    public static String errNFE(String arg) {
        return "Can not interpret \"" + arg + "\" as an index number.\n";
    }


    public static String errUnknownCmd(String cmd) {
        return "".equals(cmd) ? "Umm that's all white space? Say something! Speak to me!\n" : "Don't know what \"" +
                cmd + "\" means.\n";
    }

    /**
     * @param idx Index to program. Starts with 0.
     */
    public static String errOutOfBounds(int idx, int listSize) {
        idx += 1;
        return "Can not retrieve item with " +
                (idx > 0 ? "index " + idx + " from a list of size " + listSize : "non-positive index (" + idx + ')')
                + ".\n";
    }

}
