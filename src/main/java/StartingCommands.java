public enum StartingCommands {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE;

    /**
     * checks if the enum contains the string being passed inside
     * @param str the string to be compared to the items in the enum
     * @return boolean if the string is a valid enum
     */
    public static boolean contains(String str) {
        for (StartingCommands command:values()) {
            if (command.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
