package rick;

import rick.command.Command;
import rick.command.DateFilterCommand;
import rick.command.DeadlineCommand;
import rick.command.DeleteCommand;
import rick.command.ErrorCommand;
import rick.command.EventCommand;
import rick.command.ExitCommand;
import rick.command.FindCommand;
import rick.command.ListCommand;
import rick.command.MarkCommand;
import rick.command.TodoCommand;
import rick.command.UnmarkCommand;
import rick.exceptions.RickException;
import rick.exceptions.RickInvalidCommandException;
import rick.exceptions.RickTaskIndexMissingException;

/**
 * The main class for parsing commands given to the rick.Rick app via the
 * command line.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class Parser {
    /**
     * The main access function for creating commands given user input.
     *
     * @param line The user input.
     * @return The parsed command.
     */
    public static Command parse(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length == 1) {
            return simpleCommand(tokens[0]);
        }

        return twoArgCommand(tokens[0], tokens[1]);
    }

    /**
     * The main function for parsing commands with one word.
     *
     * @param cmd The command given.
     * @return The analysed command.
     */
    private static Command simpleCommand(String cmd) {
        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "find":
            return new ErrorCommand(
                    "An empty search was attempted. Valid Usage: find {search term}"
            );
        default:
            if (cmd.matches("(mark|unmark|delete)")) {
                return new ErrorCommand(new RickTaskIndexMissingException(cmd));
            } else {
                return new ErrorCommand(new RickInvalidCommandException());
            }
        }
    }

    /**
     * The main function for parsing commands of the following format: `"{command} {param}"`
     *
     * @param cmd The command given.
     * @param param The parameter provided to the command.
     * @return The analysed command.
     */
    public static Command twoArgCommand(String cmd, String param) {
        switch (cmd) {
        case "todo":
            return new TodoCommand(param);
        case "deadline":
            return new DeadlineCommand(param);
        case "event":
            return new EventCommand(param);
        case "tasks":
            return new DateFilterCommand(param);
        case "find":
            return new FindCommand(param);
        default:
            if (cmd.matches("(mark|unmark|delete)")) {
                return manipulateCommand(cmd, param);
            }
            return new ErrorCommand(new RickInvalidCommandException());
        }
    }

    /**
     * The main access point for commands that manipulate
     * tasks in the store.
     *
     * @param cmd The manipulation command.
     * @param param The access index.
     * @return The analysed command.
     */
    public static Command manipulateCommand(String cmd, String param) {
        try {
            int idx = Integer.parseInt(param);
            switch (cmd) {
            case "mark":
                return new MarkCommand(idx);
            case "unmark":
                return new UnmarkCommand(idx);
            case "delete":
                return new DeleteCommand(idx);
            default:
                break;
            }
        } catch (NumberFormatException e) {
            return new ErrorCommand(new RickException(
                String.format("An invalid index was provided for the %s command."
                        + " Ensure it is a number!", cmd)
            ));
        }
        return new ErrorCommand("An internal server error occurred");
    }
}
