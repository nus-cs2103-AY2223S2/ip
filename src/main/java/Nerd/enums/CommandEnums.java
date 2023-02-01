package Nerd.enums;

/**
 * Represents the enumerated commands.
 */
public enum CommandEnums {
    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DATE("date"),
    FIND("find"),
    DELETE("delete");

    private final String command;

    /**
     * Instantiates an Event Object that can be placed into the TaskList.
     *
     * @param command the input command through the User Interface.
     */
    CommandEnums(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
