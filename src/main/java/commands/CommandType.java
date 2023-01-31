package commands;

public enum CommandType {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("unknown"),
    BYE("bye");

    private final String value;

    CommandType(String value) {
        this.value = value;
    }

    public boolean equals(String input) {
        return this.value.equalsIgnoreCase(input);
    }
}
