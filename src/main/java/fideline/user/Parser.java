package fideline.user;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import fideline.exception.EmptyInputException;
import fideline.exception.EmptyParamException;
import fideline.exception.FidelineException;
import fideline.exception.InvalidArgumentException;
import fideline.exception.UnexpectedArgumentException;
import fideline.exception.UnknownCommandException;
import fideline.exception.WrongFormatException;
import fideline.execution.Command;
import fideline.execution.CreateDeadlineCommand;
import fideline.execution.CreateEventCommand;
import fideline.execution.CreateTodoCommand;
import fideline.execution.DeleteCommand;
import fideline.execution.ExitCommand;
import fideline.execution.FindCommand;
import fideline.execution.ListCommand;
import fideline.execution.MarkCommand;
import fideline.execution.UnmarkCommand;

/**
 * Manager of new and existing tasks. Handles creating, deletion and
 * status change of all tasks.
 *
 * @author Fun Leon
 */
public class Parser {

    private static final String[] possibleCommandsArr = {
        "bye", "list", "todo", "deadline", "event", "mark", "unmark", "delete", "find"
    };

    enum CommandType {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND
    }


    /**
     * Parses the user input (String), and finds the command issued by the user
     * and any relevant parameters.
     *
     * @param userInput User input as a string.
     * @return The command associated with the user input.
     * @throws FidelineException Thrown if user input is invalid for any reason.
     */

    public static Command getCommand(String userInput) throws FidelineException {

        //@@author Beebeeoii-reused
        //Reused from https://github.com/Beebeeoii/ip/blob/master/src/main/java/membot/commands/Command.java
        //with minor modifications

        if (userInput.isEmpty()) {
            throw new EmptyInputException();
        }

        String commandName = getCommandString(userInput);
        try {
            switch (CommandType.valueOf(commandName)) {
            case BYE:
                return getByeCommand(userInput);
            case LIST:
                return getListCommand(userInput);
            case TODO:
                return getTodoCommand(userInput);
            case DEADLINE:
                return getDeadlineCommand(userInput);
            case EVENT:
                return getEventCommand(userInput);
            case MARK:
                return getMarkCommand(userInput);
            case UNMARK:
                return getUnmarkCommand(userInput);
            case DELETE:
                return getDeleteCommand(userInput);
            case FIND:
                return getFindCommand(userInput);
            default:
                assert false : "command not accounted for";
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        throw new UnknownCommandException();
    }

    public static String getCommandString(String userInput) {
        String[] commandComponents = userInput.split(" ");
        String commandName = commandComponents[0].toUpperCase();
        return commandName;
    }

    public static String getArgsString(String userInput) {
        String[] commandComponents = userInput.split(" ", 2);
        String args = commandComponents[1];
        return args;
    }

    public static boolean hasNoArguments(String userInput) {
        String[] inputComponents = userInput.split(" ");
        return inputComponents.length == 1;
    }

    public static Command getByeCommand(String userInput) throws UnexpectedArgumentException {
        if (!hasNoArguments(userInput)) {
            throw new UnexpectedArgumentException();
        }
        return new ExitCommand();
    }

    public static Command getListCommand(String userInput) throws UnexpectedArgumentException {
        if (!hasNoArguments(userInput)) {
            throw new UnexpectedArgumentException();
        }
        return new ListCommand();
    }

    public static Command getTodoCommand(String userInput) throws EmptyParamException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String taskDescription = getArgsString(userInput);
        return new CreateTodoCommand(taskDescription);
    }

    public static Command getDeadlineCommand(String userInput) throws EmptyParamException,
            InvalidArgumentException, WrongFormatException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String[] argsArr = getDeadlineArgs(userInput);
        assert argsArr.length == 2 : "Deadline arguments parse error";
        return new CreateDeadlineCommand(argsArr[0], argsArr[1]);
    }

    public static String[] getDeadlineArgs(String userInput) throws InvalidArgumentException,
            WrongFormatException {
        String argsString = getArgsString(userInput);
        String[] args = argsString.split(" /by ", 2);
        try {
            String deadline = args[1];
            if (isDate(deadline)) {
                String deadlineDateTime = formatDate(deadline);
                args[1] = deadlineDateTime;
            }
            return args;
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("deadline *description* /by *timing*");
        }
    }

    public static boolean isDate(String s) {
        final String dateRegex = "\\d++/\\d++/\\d++";
        return s.matches(dateRegex);
    }

    public static String formatDate(String date) throws InvalidArgumentException,
            WrongFormatException {
        String[] timeComponents = date.split("/");
        if (timeComponents[0].length() > 2
                || timeComponents[1].length() > 2
                || timeComponents[2].length() != 4) {
            throw new WrongFormatException("deadline *description* /by *dd/mm/yyyy*");
        }
        if (timeComponents[0].length() == 1) {
            timeComponents[0] = "0" + timeComponents[0];
        }
        if (timeComponents[1].length() == 1) {
            timeComponents[1] = "0" + timeComponents[1];
        }
        final String dateTimeFormat = "%s-%s-%s";
        String dateTimeString = String.format(dateTimeFormat,
                timeComponents[2], timeComponents[1], timeComponents[0]);
        try {
            LocalDate ld = LocalDate.parse(dateTimeString);
            date = dateTimeString;
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("date");
        }
        return date;
    }

    public static Command getEventCommand(String userInput) throws EmptyParamException,
            WrongFormatException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String[] argsArr = getEventArgs(userInput);
        assert argsArr.length == 3 : "Event arguments parse error";
        return new CreateEventCommand(argsArr[0], argsArr[1], argsArr[2]);
    }

    public static String[] getEventArgs(String userInput) throws WrongFormatException {
        String argsString = getArgsString(userInput);
        String[] a = argsString.split(" /from ", 2);
        String taskDescription = a[0];
        try {
            String[] b = a[1].split(" /to ", 2);
            String startTime = b[0];
            String endTime = b[1];
            String[] args = {taskDescription, startTime, endTime};
            return args;
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("event *description* /from *start time* /to *end time*");
        }
    }

    public static Command getMarkCommand(String userInput) throws EmptyParamException,
            InvalidArgumentException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String argsString = getArgsString(userInput);
        try {
            int taskNum = Integer.parseInt(argsString);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentException("integer (e.g: mark 2)");
        }
    }

    public static Command getUnmarkCommand(String userInput) throws EmptyParamException,
            InvalidArgumentException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String argsString = getArgsString(userInput);
        try {
            int taskNum = Integer.parseInt(argsString);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentException("integer (e.g: unmark 2)");
        }
    }

    public static Command getDeleteCommand(String userInput) throws EmptyParamException,
            InvalidArgumentException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String argsString = getArgsString(userInput);
        try {
            int taskNum = Integer.parseInt(argsString);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentException("integer (e.g: delete 2)");
        }
    }

    public static Command getFindCommand(String userInput) throws EmptyParamException {
        if (hasNoArguments(userInput)) {
            throw new EmptyParamException();
        }
        String argsString = getArgsString(userInput);
        return new FindCommand(argsString);
    }

}
