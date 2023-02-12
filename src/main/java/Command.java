public enum Command {

    LIST("list", ""),
    TODO("todo", "[T]"),
    DEADLINE("deadline", "[D]"),
    EVENT("event", "[E]"),
    DELETE("delete", ""),
    MARK("mark", ""),
    SOMETHINGELSE("", ""),
    UNMARK("unmark", "");
    private final String description;
    private final String tag;
    Command(String description, String tag) {
        this.description = description;
        this.tag = tag;
    }
    public String getString() {
        return this.description;
    }
    public String getTag() {
        return this.tag;
    }
    public static Command fromString(String text) {
        for (Command b : Command.values()) {
            if (b.getString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return SOMETHINGELSE;
    }
    public static Command fromTag(String tag) {
        for (Command b : Command.values()) {
            if (b.getTag().equals(tag)) {
                return b;
            }
        }
        return SOMETHINGELSE;
    }
}
