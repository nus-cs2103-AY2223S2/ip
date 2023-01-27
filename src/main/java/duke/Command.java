package duke;

public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    FIND;

    /**
     * Checks if the string is a valid Command enum.
     *
     * @param str String to be checked.
     * @return boolean on whether it is a valid enum.
     */
    public static boolean contains(String str) {
        for (Command ac:values()) {
            if (ac.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
