package duke.command;

import duke.util.Stateful;

import java.util.function.Function;

/**
 * A command that takes arguments.
 *
 * @see Command
 */
public class ArgCommand extends Command {
    private final Function<String[], Stateful> function;

    /**
     * Creates a new ArgCommand.
     *
     * @param name     The name of the command.
     * @param helpStr  The help string of the command.
     * @param params   The named parameters of the command.
     * @param function The function to be called when the command is executed.
     */
    public ArgCommand(String name, String helpStr, String[] params, Function<String[], Stateful> function) {
        super(name, helpStr, true, params);
        this.function = function;
    }

    @Override
    public Stateful execute(String[] args) {
        return function.apply(args);
    }
}