package duke.command;

import java.util.function.Function;

/**
 * A simple command that does not take arguments.
 *
 * @see Command
 */
public class BasicCommand extends Command {
    private final Function<Boolean, String[]> function;

    /**
     * Creates a new BasicCommand.
     *
     * @param name     The name of the command.
     * @param helpStr  The help string of the command.
     * @param function The function to be called when the command is executed.
     *                 The function should take in a Boolean object used to flag program exit,
     *                 and output an array of strings.
     */
    public BasicCommand(String name, String helpStr, Function<Boolean, String[]> function) {
        super(name, helpStr, false, new String[]{});
        this.function = function;
    }

    @Override
    public String[] execute(String[] params, Boolean hasQuit) {
        return function.apply(hasQuit);
    }
}
