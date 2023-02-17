package duke.model.command;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Keyword {

    // @formatter:off
    BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
    UNMARK("unmark"), DELETE("delete"), FIND("find"), SORT_DEADLINES("sort-deadlines"), CLEAR("clear"),
    SET_DESCRIPTION("set-description"), HELP("help"), UNKNOWN(null);
    // @formatter:on

    private final String value;

    private Keyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Keyword> findKeyword(String value) {
        return Arrays.stream(values())
                .filter(keyword -> Objects.equals(keyword.value, value))
                .findFirst();
    }
}
