package duke.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Parser to parse strings into commands.
 */
public class Parser {

    /**
     * Parses a string into a command.
     *
     * @param input Input string to parse into command.
     * @return Parsed command.
     */
    public static Command parse(String input)
            throws IllegalArgumentException {
        LinkedHashMap<Command.Argument, String> arguments = new LinkedHashMap<>();
        for (String term : input.strip().split(" /")) {
            int firstSpace = term.indexOf(" ");
            Command.Argument argument = Command.parseArgument((firstSpace == -1
                    ? term
                    : term.substring(0, firstSpace)));
            String value = (firstSpace == -1
                    ? ""
                    : term.substring(firstSpace + 1));
            arguments.put(argument, value);
        }
        Command command = new Command(arguments);
        checkArguments(command);
        return command;
    }

    /**
     * Checks if the Command's arguments are valid.
     *
     * @param command Command to check.
     * @throws IllegalArgumentException if arguments are invalid.
     */
    private static void checkArguments(Command command)
            throws IllegalArgumentException {
        ArrayList<Command.Argument> requiredArgs = new ArrayList<>();
        ArrayList<Command.Argument> requiredValues = new ArrayList<>();
        switch (command.getName()) {
        case BYE:
            // Fallthrough
        case LIST:
            break;
        case MARK:
            requiredValues.add(Command.Argument.MARK);
            break;
        case DELETE:
            requiredValues.add(Command.Argument.DELETE);
            break;
        case TODO:
            requiredValues.add(Command.Argument.TODO);
            break;
        case DEADLINE:
            requiredArgs.add(Command.Argument.BY);
            requiredValues.add(Command.Argument.DEADLINE);
            requiredValues.add(Command.Argument.BY);
            break;
        case EVENT:
            requiredArgs.add(Command.Argument.FROM);
            requiredArgs.add(Command.Argument.TO);
            requiredValues.add(Command.Argument.EVENT);
            requiredValues.add(Command.Argument.FROM);
            requiredValues.add(Command.Argument.TO);
            break;
        default:
            assert false : "Unhandled command: " + command;
        }
        checkHasArgs(command, requiredArgs);
        checkHasValues(command, requiredValues);
    }

    /**
     * Checks if a command contains all the specified arguments.
     *
     * @param command Command to check.
     * @param args Required arguments.
     * @throws IllegalArgumentException if any of the required arguments are not in
     *                                  the command.
     */
    private static void checkHasArgs(Command command, ArrayList<Command.Argument> args)
            throws IllegalArgumentException {
        for (Command.Argument arg : args) {
            if (!command.hasArg(arg)) {
                throw new IllegalArgumentException("Command " + command.getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }

    /**
     * Checks if a command's arguments contain non-empty values.
     * @param command Command to check.
     * @param args Arguments with required values.
     * @throws IllegalArgumentException if any of the arguments  are not in the
     *                                  command or have empty values.
     */
    private static void checkHasValues(Command command, ArrayList<Command.Argument> args)
            throws IllegalArgumentException {
        for (Command.Argument arg : args) {
            if (!command.hasValue(arg)) {
                throw new IllegalArgumentException("Command " + command.getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }
}
