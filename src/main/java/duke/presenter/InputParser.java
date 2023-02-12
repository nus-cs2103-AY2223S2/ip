package duke.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.CommandFactory;
import duke.command.CommandFactory.CommandType;
import duke.command.exceptions.InvalidParameterError;
import duke.interfaces.Command;
import duke.interfaces.CommandEventListener;
import duke.presenter.exceptions.InvalidInputError;
import duke.presenter.exceptions.ParserError;
import duke.presenter.exceptions.UnsupportedCommandError;

/**
 * A class that provides functionality to parse user input.
 * @author jayanth
 */
public class InputParser {
    private final CommandEventListener exitEventListener;
    private final CommandFactory commandFactory;

    InputParser(CommandEventListener exitEventListener, CommandFactory commandFactory) {
        this.exitEventListener = exitEventListener;
        this.commandFactory = commandFactory;
    }

    Command parseInput(String input) throws ParserError {
        if (input.isEmpty()) {
            throw new InvalidInputError("Oops! Your input was empty");
        }
        String deadlineRegex = "^deadline (.*?)(?: /by (.*))$";
        String eventRegex = "^event (.*?)(?: /from (.*?) /to (.*))$";
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern eventPattern = Pattern.compile(eventRegex);
        String[] tokens = input.split(" ");
        try {
            switch (tokens[0]) {
            case "bye":
                return handleByeCommand();
            case "list":
                return handleListCommand(tokens);
            case "todo":
                return handleTodoCommand(input, tokens);
            case "find":
                return handleFindCommand(input, tokens);
            case "deadline":
                return handleDeadlineCommand(input, deadlinePattern);
            case "event":
                return handleEventCommand(input, eventPattern);

            case "mark":
                return handleMarkCommand(tokens);
            // handle improper arguments
            case "unmark":
                return handleUnmarkCommand(tokens);
            case "delete":
                return handleDeleteCommand(tokens);
            default:
                throw new UnsupportedCommandError();
            }
        } catch (InvalidParameterError e) {
            throw new InvalidInputError(e.getMessage());
        }
    }

    private Command handleDeleteCommand(String[] tokens) throws InvalidInputError, InvalidParameterError {
        if (tokens.length != 2) {
            throw new InvalidInputError();
        }
        return commandFactory.createCommand(CommandType.DELETE_TASK, tokens[1]);
    }

    private Command handleUnmarkCommand(String[] tokens) throws InvalidInputError, InvalidParameterError {
        if (tokens.length != 2) {
            throw new InvalidInputError();
        }
        return commandFactory.createCommand(CommandType.MARK_UNDONE, tokens[1]);
    }

    private Command handleMarkCommand(String[] tokens) throws InvalidInputError, InvalidParameterError {
        if (tokens.length != 2) {
            throw new InvalidInputError();
        } else {
            return commandFactory.createCommand(CommandType.MARK_DONE, tokens[1]);
        }
    }

    private Command handleEventCommand(String input, Pattern eventPattern)
            throws InvalidParameterError, InvalidInputError {
        Matcher matcher;
        matcher = eventPattern.matcher(input);
        if (matcher.matches()) {
            String eventDescription = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);
            return commandFactory.createCommand(CommandType.CREATE_EVENT,
                    eventDescription, startTime, endTime);
        } else {
            String invalidEventMessage = "Oops! Your input did not match the format for an event";
            throw new InvalidInputError(invalidEventMessage);
        }
    }

    private Command handleDeadlineCommand(String input, Pattern deadlinePattern)
            throws InvalidParameterError, InvalidInputError {
        Matcher matcher;
        matcher = deadlinePattern.matcher(input);
        // handle matcher not matching
        if (matcher.matches()) {
            String taskDescription = matcher.group(1);
            String deadline = matcher.group(2);
            return commandFactory.createCommand(CommandType.CREATE_DEADLINE,
                    taskDescription, deadline);
        } else {
            String invalidDeadlineMessage = "Oops! Your input did not match the format for a deadline";
            throw new InvalidInputError(invalidDeadlineMessage);
        }
    }

    private Command handleFindCommand(String input, String[] tokens) throws InvalidParameterError {
        if (tokens.length == 1) {
            throw new InvalidParameterError("Oops! Search string cannot be empty");
        }
        return commandFactory.createCommand(CommandType.FIND,
                input.substring(4).strip());
    }

    private Command handleTodoCommand(String input, String[] tokens)
            throws InvalidInputError, InvalidParameterError {
        if (tokens.length == 1) {
            throw new InvalidInputError("Oops! Todo cannot be empty");
        }
        return commandFactory.createCommand(CommandType.CREATE_TODO,
                input.substring(4).strip());
    }

    private Command handleListCommand(String[] tokens) throws InvalidParameterError, UnsupportedCommandError {
        if (tokens.length == 1) {
            return commandFactory.createCommand(CommandType.LIST);
        } else if (tokens.length == 2) {
            return commandFactory.createCommand(CommandType.LIST, tokens[1]);
        } else {
            throw new UnsupportedCommandError();
        }
    }

    private Command handleByeCommand() throws InvalidParameterError {
        exitEventListener.onCommandEvent("bye");
        return commandFactory.createCommand(CommandType.BYE);
    }
}
