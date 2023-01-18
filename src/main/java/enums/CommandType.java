package enums;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    INVALID("invalid");
    private final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
