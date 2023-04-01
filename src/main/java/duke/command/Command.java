package duke.command;

import java.util.LinkedHashMap;

/**
 * Command containing its arguments and their values.
 */
public class Command {

    /**
     * Types of command arguments.
     */
    public enum Argument {
        BYE,
        TODO,
        DEADLINE,
        BY,
        EVENT,
        FROM,
        TO,
        LIST,
        MARK,
        FIND,
        DELETE,
        NO_OP
    }

    /** Arguments of the command */
    private final LinkedHashMap<Argument, String> arguments;

    /**
     * Constructs a new command.
     * The first param is the name of the command.
     *
     * @param arguments Arguments of the command.
     */
    public Command(LinkedHashMap<Argument, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns the name of command.
     *
     * @return Name of command.
     */
    public Argument getName() {
        return arguments.entrySet().iterator().next().getKey();
    }

    /**
     * Returns the value of an argument.
     *
     * @param arg Argument to retrieve value of.
     * @return Value of the argument.
     */
    public String getArgumentValue(Argument arg) {
        return arguments.get(arg);
    }

    /**
     * Parses the argument string and returns the argument enumeration.
     * Input string is case-insensitive and empty string represents
     * the NO-OP command.
     *
     * @param arg Argument in string.
     * @return Argument in enumeration.
     * @throws IllegalArgumentException If input string is not a valid enum.
     */
    public static Argument parseArgument(String arg)
            throws IllegalArgumentException {
        if (arg.equals("")) {
            return Argument.NO_OP;
        }
        try {
            return Argument.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Command "
                    + arg + " not recognised");
        }
    }

    /**
     * Checks if the command contains an argument key.
     *
     * @param arg Argument to check.
     * @return true If the argument exists, false otherwise.
     */
    public boolean hasArg(Argument arg) {
        return arguments.containsKey(arg);
    }

    /**
     * Checks if a specific argument in command has a value.
     *
     * @param arg Argument to check.
     * @return true If argument exists and value is a non-empty string,
     *         false otherwise.
     */
    public boolean hasValue(Argument arg) {
        return arguments.containsKey(arg) && !arguments.get(arg).equals("");
    }
}
