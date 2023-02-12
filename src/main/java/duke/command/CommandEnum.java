package duke.command;
public enum CommandEnum {
    LIST("list", ""),
    TODO("todo", "[T]"),
    DEADLINE("deadline", "[D]"),
    EVENT("event", "[E]"),
    DELETE("delete", ""),
    MARK("mark", ""),
    FIND("find", ""),
    SOMETHINGELSE("", ""),
    UNMARK("unmark", "");
    private final String description;
    private final String tag;
    CommandEnum(String description, String tag) {
        this.description = description;
        this.tag = tag;
    }
    public String getString() {
        return this.description;
    }
    public String getTag() {
        return this.tag;
    }
    public static CommandEnum fromString(String text) {
        for (CommandEnum b : CommandEnum.values()) {
            if (b.getString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return SOMETHINGELSE;
    }
    public static CommandEnum fromTag(String tag) {
        for (CommandEnum b : CommandEnum.values()) {
            if (b.getTag().equals(tag)) {
                return b;
            }
        }
        return SOMETHINGELSE;
    }
}
