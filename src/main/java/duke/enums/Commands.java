package duke.enums;

/**
 * Command strings
 */
public enum Commands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BY("/by"),
    FROM("/from"),
    TO("/to"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEL("del"),
    FIND("find");

    private final String cmd;

    /**
     * @param cmd given in the enum
     */
    Commands(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Change the command to the user input thing
     *
     * @return String of the command
     */
    public String cmd() {
        return cmd;
    }

    public int len() {
        return cmd.length();
    }
}
