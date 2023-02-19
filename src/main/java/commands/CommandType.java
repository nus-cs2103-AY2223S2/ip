package commands;

/**
 * Represents the possible types of commands that Sirius can understand.
 */
public enum CommandType {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    UNKNOWN("unknown"),
    BYE("bye");

    private final String value;

    /**
     * Constructs a new CommandType with the specified value.
     *
     * @param value the value of this CommandType
     */
    CommandType(String value) {
        this.value = value;
    }

    /**
     * Returns whether this CommandType's value is equal to the specified input, ignoring case.
     *
     * @param input the input to compare with this CommandType's value
     * @return whether this CommandType's value is equal to the specified input, ignoring case
     */
    public boolean isEqual(String input) {
        return this.value.equalsIgnoreCase(input);
    }

    public String getValue() {
        return value;
    }
}
