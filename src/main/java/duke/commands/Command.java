package duke.commands;

import java.util.Stack;

/**
 * Command represents a command to be executed.
 */
public abstract class Command {
    /**
     * Store commands in a stack in a FIFO manner.
     */
    private static Stack<Command> pastCommands = new Stack<Command>();

    /**
     * Adds command to the stack.
     *
     * @param pastCommand The command to add.
     */
    public static void addPastCommand(Command pastCommand) {
        pastCommands.add(pastCommand);
    }

    public static Command getPastCommand() {
        return pastCommands.pop();
    }

    /**
     * Returns a boolean based on whether the list of past commands
     * are empty
     *
     * @return boolean based on past commands list
     */
    public static Boolean isEmpty() {
        if (pastCommands.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Undoes the command.
     *
     * @return Response message.
     */
    public abstract String undo();

    /**
     * Performs an action in response to the command.
     */
    public abstract String action();
}
