package berry.parser;

import berry.command.Command;
import berry.command.AddTaskCommand;
import berry.command.DeleteCommand;
import berry.command.ExitCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.command.UnmarkCommand;
import berry.command.FindCommand;
import berry.exception.BerryException;
import berry.exception.EmptyClauseException;
import berry.exception.EmptyDescriptionException;
import berry.exception.UnknownCommandException;
import berry.exception.MissingClauseException;

import berry.task.Deadline;
import berry.task.Event;
import berry.task.Todo;

/**
 * Parses user input into {@code Commands}.
 */
public class Parser {
    /**
     * Types of commands.
     */
    private enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE
    }

    /**
     * Parses user input into a {@code Command}.
     *
     * @param userInput is the full user input string
     * @return command based on the user input
     * @throws BerryException if the given string cannot be parsed
     */
    public static Command parseInput(String userInput) throws BerryException {
        String[] splitDescriptionAndDetails = userInput.split(" ", 2);
        String[] splitDetails;
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(splitDescriptionAndDetails[0].toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException();
        }

        validate(commandType, userInput);

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(splitDescriptionAndDetails[1]));
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(splitDescriptionAndDetails[1]));
        case TODO:
            return new AddTaskCommand(new Todo(splitDescriptionAndDetails[1]));
        case DEADLINE:
            splitDetails = splitDescriptionAndDetails[1].split(" /by ");
            return new AddTaskCommand(new Deadline(splitDetails[0], splitDetails[1]));
        case EVENT:
            splitDetails = splitDescriptionAndDetails[1].split(" /from ");
            String[] splitFurtherDetails = splitDetails[1].split(" /to ");
            return new AddTaskCommand(new Event(splitDetails[0], splitFurtherDetails[0], splitFurtherDetails[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(splitDescriptionAndDetails[1]));
        case FIND:
            return new FindCommand(splitDescriptionAndDetails[1]);
        default:
            throw new UnknownCommandException();
        }

    }

    /**
     * Checks if the given arguments are valid for its task to be created.
     *
     * @param commandType is the type of the task
     * @param input is the full user input string
     * @throws BerryException if the given string has missing or incomplete arguments
     */
    private static void validate(CommandType commandType, String input) throws BerryException {
        String command = input.split(" ")[0];
        System.out.println("command type: " + commandType);

        if (!(commandType == CommandType.TODO ||
                commandType == CommandType.DEADLINE ||
                commandType == CommandType.EVENT)) {
            return;
        }

        switch(commandType) {
        case TODO:
            if (input.substring(4).isBlank()) {
                throw new EmptyDescriptionException(command);
            }
            break;
        case DEADLINE:
            if (!input.contains("/by ") || !input.contains("/by")) {
                throw new MissingClauseException("by");
            } else if (input.split("/by")[0].substring(8).isBlank()) {
                throw new EmptyDescriptionException(command);
            } else if (input.endsWith("/by") || input.split("/by")[1].isBlank()) {
                throw new EmptyClauseException("by");
            }
            break;
        case EVENT:
            if (!input.contains("/from ") || !input.contains("/from")) {
                throw new MissingClauseException("from");
            } else if (input.split("/from")[0].substring(5).isBlank()) {
                throw new EmptyDescriptionException(command);
            } else if (!input.contains("/to ") || !input.contains("/to")) {
                throw new MissingClauseException("to");
            } else if (input.endsWith("/to") || input.split("/to")[1].isBlank()) {
                throw new EmptyClauseException("to");
            } else if (input.endsWith("/from") || input.split("/to")[0].split("/from")[1].isBlank()) {
                throw new EmptyClauseException("from");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
