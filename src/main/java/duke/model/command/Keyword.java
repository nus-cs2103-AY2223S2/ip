package duke.model.command;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Keyword {

    // @formatter:off
    BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
    FIND("find"), UNMARK("unmark"), DELETE("delete"), SORT("sort"), CLEAR("clear"), EDIT("edit"),
    HELP("help");
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
