public enum StartingCommands {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE;

    public static boolean contains(String str) {
        for (StartingCommands command:values()) {
            if (command.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
