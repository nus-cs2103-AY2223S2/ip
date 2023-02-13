package duke.command;

import duke.exceptions.UnknownCommandException;

/**
 * Commands that can be used.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    GETEVENTSON("geteventson"),
    FIND("find"),
    GETREMINDERS("getreminders");

    private String name;

    /**
     * Constructor for defined enumeration.
     * @param name Name of the constant.
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Gets the command that correspond with the given name.
     * @param name Name of the command.
     * @return The corresponding command if valid.
     * @throws UnknownCommandException if the name provided is not associated with any command.
     */
    public static Command get(String name) throws UnknownCommandException {
        for (Command c : values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        throw new UnknownCommandException(name);
    }
}
