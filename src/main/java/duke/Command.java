package duke;

public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE;

    /**
     * Checks if the type is a valid Command.
     *
     * @param str String representation of the type.
     * @return Whether str is valid Command.
     */
    public static boolean isCommand(String str) {
        for (Command ac:values()) {
            if (ac.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
