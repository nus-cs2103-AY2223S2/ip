package angela.commands;

import angela.exception.AngelaException;
import angela.exception.InvalidCommandAngelaException;

/**
 * Commands accepted.
 */
public enum CommandType {
    EXIT("bye"),
    DISPLAY_LIST("list"),
    MARK_TASK_AS_DONE("mark"),
    MARK_TASK_AS_UNDONE("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    REMINDERS("remind");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    /**
     * Converts the given string to a command type.
     * @param command A string that represents the command type.
     * @return A command type.
     * @throws AngelaException If the given string is cannot be converted to a command type.
     */
    public static CommandType getCommandType(String command) throws AngelaException {
        CommandType[] values = values();
        for (CommandType value : values) {
            if (command.equals(value.command)) {
                return value;
            }
        }
        throw new InvalidCommandAngelaException();
    }
}
