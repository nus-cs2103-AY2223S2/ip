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
    DEL("del");

    private final String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }

    public String cmd() {
        return cmd;
    }
}