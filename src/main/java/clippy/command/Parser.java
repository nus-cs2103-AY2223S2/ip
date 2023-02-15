package clippy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import clippy.exception.ClippyException;
import clippy.exception.ClippyInvalidDateException;
import clippy.exception.ClippyInvalidEventException;
import clippy.exception.ClippyMissingDeadlineException;
import clippy.exception.ClippyTodoEmptyDescriptionException;
import clippy.exception.ClippyUnknownCommandException;
import clippy.task.PriorityLevel;

/**
 * Parser to parse all user commands.
 *
 * @author chunzkok
 */
public class Parser {
    private static final String[] EMPTY_ARG_LIST = new String[]{};
    /**
     * Identifies the correct Command handler to be invoked based on the user input.
     *
     * @param command The command given by the user.
     * @return A Command instance containing the corresponding command handler.
     * @throws ClippyException
     */
    public static Command parse(String command) throws ClippyException {
        assert !command.equals("") : "user input should not be empty";

        String[] args = command.split(" ");
        switch (args[0]) {
        case "bye":
            return dispatch(CommandType.BYE, EMPTY_ARG_LIST);
        case "mark":
            // todo: check if second argument is a valid number
            return dispatch(CommandType.MARK, new String[] { args[1] });
        case "unmark":
            // todo: check if second argument is a valid number
            return dispatch(CommandType.UNMARK, new String[] { args[1] });
        case "todo":
            if (args.length < 2) {
                throw new ClippyTodoEmptyDescriptionException();
            }
            return dispatch(CommandType.TODO, new String[] { command.substring(5)});
        case "deadline": {
            int byIndex = command.indexOf("/by ");
            if (byIndex == -1 || command.length() < byIndex + 4) {
                throw new ClippyMissingDeadlineException();
            }
            String description = command.substring(9, byIndex).trim();
            String byDateString = command.substring(byIndex + 4).trim();
            try {
                LocalDate byDate = LocalDate.parse(byDateString);
                return dispatch(CommandType.DEADLINE, new String[] { description }, byDate);
            } catch (DateTimeParseException e) {
                throw new ClippyInvalidDateException();
            }
        }
        case "event": {
            int fromIndex = command.indexOf("/from ");
            int toIndex = command.indexOf("/to ");
            if (fromIndex == -1
                    || toIndex == -1
                    || toIndex - fromIndex < 5
                    || command.length() - toIndex < 4) {
                throw new ClippyInvalidEventException();
            }

            String description = command.substring(6, fromIndex).trim();
            String fromDateString = command.substring(fromIndex + 6, toIndex).trim();
            String toDateString = command.substring(toIndex + 4).trim();
            try {
                LocalDate fromDate = LocalDate.parse(fromDateString);
                LocalDate toDate = LocalDate.parse(toDateString);
                return dispatch(CommandType.EVENT, new String[] { description }, fromDate, toDate);
            } catch (DateTimeParseException e) {
                throw new ClippyInvalidDateException();
            }

        }
        case "list":
            return dispatch(CommandType.LIST, EMPTY_ARG_LIST);
        case "find":
            // todo: check if args[1] is valid input
            return dispatch(CommandType.FIND, new String[] { args[1].trim() });
        case "delete":
            // todo: check for valid list index
            return dispatch(CommandType.DELETE, new String[]{ args[1] });
        case "priority":
            // todo: check if args[1] is a valid task id
            if (args.length < 3) {
                throw new ClippyException("Too little arguments provided!\n"
                        + "Expected format: priority <taskID> <priorityLevel>");
            }
            try {
                PriorityLevel priority = PriorityLevel.valueOf(args[2]);
            } catch (IllegalArgumentException e) {
                throw new ClippyException("Invalid priority, "
                        + "please select one from the following: \n"
                        + "URGENT, HIGH, MEDIUM, LOW");
            }

            return dispatch(CommandType.PRIORITY, new String[]{ args[1], args[2] });
        default:
            throw new ClippyUnknownCommandException();
        }
    }

    /**
     * Instantiates the correct command handler object to be returned, assuming
     * inputs are all valid.
     *
     * @param commandType The type of command given.
     * @param args The arguments to the command.
     * @param dates Dates, if any, to be used in the command.
     * @return
     */
    private static Command dispatch(CommandType commandType, String[] args, LocalDate... dates) {
        assert commandType != null : "commandType should not be null";

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(args[0]));
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(args[0]));
        case TODO:
            return new AddToDoCommand(args[0]);
        case DEADLINE:
            return new AddDeadlineCommand(args[0], dates[0]);
        case EVENT:
            return new AddEventCommand(args[0], dates[0], dates[1]);
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(Integer.parseInt(args[0]));
        case FIND:
            return new FindCommand(args[0]);
        case PRIORITY:
            return new SetPriorityCommand(Integer.parseInt(args[0]), PriorityLevel.valueOf(args[1]));
        default:
            return null;
        }
    }
}
