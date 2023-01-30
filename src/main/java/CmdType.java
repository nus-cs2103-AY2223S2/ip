public enum CmdType {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String cmd;

    CmdType(String str) {
        this.cmd = str;
    }

    @Override
    public String toString() {
        return cmd;
    }

    public int len() {
        return cmd.length();
    }
}
