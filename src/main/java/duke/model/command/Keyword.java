package duke.model.command;

import java.util.Arrays;

public enum Keyword {

    // @formatter:off
    BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
    UNMARK("unmark"), DELETE("delete"), FIND("find"), SORT_DEADLINES("sort-deadlines"), CLEAR("clear"),
    SET_DESCRIPTION("set-description"), HELP("help"), UNKNOWN("");
    // @formatter:on

    private final String value;

    private Keyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Keyword findKeyword(String value) {
        return Arrays.stream(values())
                .filter(keyword -> keyword.value.equals(value))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
