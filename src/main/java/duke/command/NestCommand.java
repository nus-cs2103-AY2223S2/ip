package duke.command;

import duke.util.Stateful;

import java.util.HashMap;
import java.util.Queue;
import java.util.function.BiFunction;

/**
 * A (nested) command that has subcommands.
 *
 * @see Command
 */
public class NestCommand extends Command{

    private final HashMap<String, Command> subcommands;

    /**
     * Creates a new NestCommand.
     *
     * @param name         The name of the command.
     * @param helpStr      The help string of the command.
     * @param function     The function that the command, given an input, performs.
     * @param commands     The subcommands of the command.
     */
    public NestCommand(String name, String helpStr, BiFunction<Stateful, Queue<String>, Stateful> function, Command[] commands) {
        super(name, helpStr, true, function);
        this.subcommands = Command.makeCommandMap(commands);
    }

    public NestCommand(String name, String helpStr, Command[] commands) {
        super(name, helpStr, true, (stateful, strings) -> stateful);
        this.subcommands = Command.makeCommandMap(commands);
    }

    /**
     * Returns the subcommands of the command.
     *
     * @return The subcommands of the command.
     */
    public HashMap<String, Command> getSubCommands() {
        return subcommands;
    }
}
