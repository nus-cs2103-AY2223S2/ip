public enum AddCommands {
    TODO,
    DEADLINE,
    EVENT;

    public static boolean contains(String str) {
        for (AddCommands ac:values()) {
            if (ac.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
