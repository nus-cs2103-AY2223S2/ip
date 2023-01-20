package command;

import java.util.ArrayList;
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
        DELETE,
        NO_OP
    }

    /** Arguments of the command */
    private final LinkedHashMap<Argument, String> arguments;

    /**
     * Constructs a new command.
     *
     * @param input Input string of the command.
     * @throws IllegalArgumentException If input string is not a valid command.
     */
    public Command(String input)
        throws IllegalArgumentException {
        arguments = new LinkedHashMap<>();
        for (String term : input.strip().split(" /")) {
            int firstSpace = term.indexOf(" ");
            Argument argument = parseArgument((firstSpace == -1
                    ? term
                    : term.substring(0, firstSpace)));
            String value = (firstSpace == -1
                    ? ""
                    : term.substring(firstSpace + 1));
            arguments.put(argument, value);
        }
        checkArguments();
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
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

    private Argument parseArgument(String arg)
            throws IllegalArgumentException {
        if (arg.equals("")) {
            return Argument.NO_OP;
        }
        try {
            return Argument.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("command.Command "
                    + arg + " not recognised");
        }
    }

    private void checkArguments()
            throws IllegalArgumentException {
        ArrayList<Argument> requiredArgs = new ArrayList<>();
        ArrayList<Argument> requiredValues = new ArrayList<>();
        switch (getName()) {
        case BYE:
            // Fallthrough
        case LIST:
            break;
        case MARK:
            requiredValues.add(Argument.MARK);
            break;
        case DELETE:
            requiredValues.add(Argument.DELETE);
            break;
        case TODO:
            requiredValues.add(Argument.TODO);
            break;
        case DEADLINE:
            requiredArgs.add(Argument.BY);
            requiredValues.add(Argument.DEADLINE);
            requiredValues.add(Argument.BY);
            break;
        case EVENT:
            requiredArgs.add(Argument.FROM);
            requiredArgs.add(Argument.TO);
            requiredValues.add(Argument.EVENT);
            requiredValues.add(Argument.FROM);
            requiredValues.add(Argument.TO);
            break;
        }
        checkHasOnlyArgs(requiredArgs);
        checkHasValues(requiredValues);
    }

    private void checkHasOnlyArgs(ArrayList<Argument> args)
            throws IllegalArgumentException {
        if (arguments.size() != args.size() + 1) {
            throw new IllegalArgumentException("command.Command " + getName()
                    + " takes in " + (args.size() + 1) + " argument(s) but "
                    + arguments.size() + " were given");
        }
        for (Argument arg : args) {
            if (!arguments.containsKey(arg)) {
                throw new IllegalArgumentException("command.Command " + getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }

    private void checkHasValues(ArrayList<Argument> args)
            throws IllegalArgumentException {
        for (Argument arg : args) {
            if (!arguments.containsKey(arg) || arguments.get(arg).equals("")) {
                throw new IllegalArgumentException("command.Command " + getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }
}
