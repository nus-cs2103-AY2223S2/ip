/**
 * Defines classes that represent commands that Duke recognizes.
 * Commands are either {@link duke.command.BasicCommand}s, which do not
 * accept arguments; or {@link duke.command.ArgCommand}s, which do.
 *
 * @see duke.Duke
 */

package duke.command;

import duke.util.Stateful;
import duke.util.Ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a command that Duke recognizes.
 * Apart from possible reads/writes to data, a command when given an input may perform a modification on a
 * {@link Stateful} object via a (bi)function which can be writing to output, changing the state of the program, or both.
 */
public class Command {
    private final String name;

    private final String helpStr;
    private final boolean hasSubCommands;
    private BiFunction<Stateful, Queue<String>, Stateful> function;

    /**
     * Creates a new Command.
     *
     * @param name              The name of the command.
     * @param helpStr           The help string of the command.
     * @param hasSubCommands    Whether the command has subcommands.
     * @param function          The function that the command, given an input, performs.
     */
    public Command(String name, String helpStr, boolean hasSubCommands, BiFunction<Stateful, Queue<String>, Stateful> function) {
        this.name = name;
        this.helpStr = helpStr;
        this.hasSubCommands = hasSubCommands;
        this.function = function;
    }

    public Command(String name, String helpStr, BiFunction<Stateful, Queue<String>, Stateful> function) {
        this(name, helpStr, false, function);
    }

    public Command(String name, String helpStr, Function<Stateful, Stateful> function) {
        this(name, helpStr, false, Command.makeStateful(function));
    }

    public Command(String name, String helpStr, Supplier<Stateful> supplier) {
        this(name, helpStr, false, Command.makeStateful(supplier));
    }

    private static BiFunction<Stateful, Queue<String>, Stateful> makeStateful (Supplier<Stateful> supplier) {
        return (stateful, string) ->  stateful.next(supplier.get().getOutputs());
    }

    private static BiFunction<Stateful, Queue<String>, Stateful> makeStateful(Function<Stateful, Stateful> function) {
        return (stateful, string) -> function.apply(stateful);
    }

    /**
     * Returns the help text of the command (command name + help string)
     * Used by the {@link Ui} class to generate the help message.
     *
     * @return The help text of the command.
     */
    public String getHelpText() {
        return String.format("\t%4s : %s", this.name, this.helpStr);
    }

    /**
     * Returns the name of the command.
     *
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * True if the command has subcommands.
     * Alternative would be to type-test between {@link NestCommand} and {@link Command} :(
     *
     * @return True if the command accepts at least one subcommand.
     */
    public boolean hasSubCommands() {
        return hasSubCommands;
    }

    /**
     * Returns the State function that the command performs.
     *
     * @return The State function that the command performs.
     */
    public BiFunction<Stateful, Queue<String>, Stateful> getFunction() {
        return function;
    }

    /**
     * Sets the State function that the command performs.
     *
     * @param function  The State function that the command performs.
     */
    public void setFunction(BiFunction<Stateful, Queue<String>, Stateful> function) {
        this.function = function;
    }

    /**
     * Transforms the command's state+output bifunction into a partial state function by supplying one of the arguments
     * (the input). Used to simplify the composition and execution of multiple commands.
     *
     * @param input The input, as a list of words.
     * @return      The partial function upon supplying the input.
     * @see         duke.util.Parser#parseArgs(Queue, NestCommand) 
     */
    public Function<Stateful, Stateful> getFurnishedFunction(Queue<String> input) {
        return stateful -> function.apply(stateful, input);
    }

    /**
     * Utility method to create a name-dictionary of commands from an array of commands.
     *
     * @param commands A array of commands.
     * @return         A dictionary of commands, keyed by names.
     */
    public static HashMap<String, Command> makeCommandMap(Command[] commands) {
        return Arrays.stream(commands)
                .collect(HashMap::new,
                        (map, command) -> map.put(command.getName(), command),
                        HashMap::putAll
                );
    }

    /**
     * Each command encapsulates an operation that Duke can perform, as a function that may accept parameters. This
     * method executes the operation.
     *
     * @param stateful  The parameters (and program state) to pass to the operation.
     * @return          The output of the operation (and any side effects).
     */
    public Stateful execute(Stateful stateful, Queue<String> input) {
        return function.apply(stateful, input);
    }
}