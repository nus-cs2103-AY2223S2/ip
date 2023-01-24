package duke.command;

import duke.exceptions.UnknownCommandException;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    GETEVENTSON("geteventson");

    private String name;

    Command(String name) {
        this.name = name;
    }

    public static Command get(String name) throws UnknownCommandException {
        for (Command c : values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        throw new UnknownCommandException(name);
    }
}