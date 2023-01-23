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

    public static boolean contains(String str) {
        for (Command ac:values()) {
            if (ac.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
