package presenter;

import command.CommandFactory;
import command.exceptions.InvalidParameterError;
import interfaces.Command;
import command.CommandFactory.CommandType;
import interfaces.CommandEventListener;
import presenter.exceptions.InvalidInputError;
import presenter.exceptions.ParserError;
import presenter.exceptions.UnsupportedCommandError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Matcher matcher;
        try {
            switch (tokens[0]) {
                case "bye":
                    exitEventListener.onCommandEvent(input);
                    return commandFactory.createCommand(CommandType.BYE);
                case "greet":
                    return commandFactory.createCommand(CommandType.GREET);
                case "list":
                    return commandFactory.createCommand(CommandType.LIST);
                case "todo":
                    if (tokens.length == 1) {
                        throw new InvalidInputError("Oops! Todo cannot be empty");
                    }
                    return commandFactory.createCommand(CommandType.CREATE_TODO,
                            input.substring(4).strip());
                case "deadline":
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
                case "event":
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

                case "mark":
                    if (tokens.length != 2) {
                        throw new InvalidInputError();
                    } else {
                        return commandFactory.createCommand(CommandType.MARK_DONE, tokens[1]);
                    }
                    // handle improper arguments
                case "unmark":
                    if (tokens.length != 2) {
                        throw new InvalidInputError();
                    }
                    return commandFactory.createCommand(CommandType.MARK_UNDONE, tokens[1]);
                case "delete":
                    if (tokens.length != 2) {
                        throw new InvalidInputError();
                    }
                    return commandFactory.createCommand(CommandType.DELETE_TASK, tokens[1]);
                default:
                    throw new UnsupportedCommandError();
            }
        } catch (InvalidParameterError e) {
            throw new InvalidInputError(e.getMessage());
        }

//        String taskRegex = "^(.*?)(?: \\/by (.*)| \\/from (.*?) \\/to (.*))?$";
    }
}
