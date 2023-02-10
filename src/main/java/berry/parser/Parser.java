package berry.parser;

import berry.command.Command;
import berry.command.AddTaskCommand;
import berry.command.DeleteCommand;
import berry.command.ExitCommand;
import berry.command.FindCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.command.UnmarkCommand;
import berry.exception.BerryException;
import berry.exception.EmptyClauseException;
import berry.exception.EmptyDescriptionException;
import berry.exception.MissingClauseException;
import berry.exception.UnknownCommandException;
import berry.tag.TagList;
import berry.task.Deadline;
import berry.task.Event;
import berry.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        assert userInput != null : "Input cannot be null";
        assert userInput.length() > 0 : "Input must contain at least one element";

        ArrayList<String> listOfDescriptionAndDetails = splitDescriptionAndDetails(userInput);
        CommandType commandType;
        TagList listOfTags = new TagList();

        try {
            commandType = CommandType.valueOf(listOfDescriptionAndDetails.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }

        validateCommand(commandType, userInput);

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(listOfDescriptionAndDetails.get(1)));
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(listOfDescriptionAndDetails.get(1)));
        case TODO:
            return new AddTaskCommand(new Todo(listOfDescriptionAndDetails.get(1)));
        case DEADLINE:
            ArrayList<String> listOfDescriptionAndByDate = splitDescriptionAndByDate(listOfDescriptionAndDetails.get(1));
            return new AddTaskCommand(new Deadline(listOfDescriptionAndByDate.get(0),
                    listOfDescriptionAndByDate.get(1)));
        case EVENT:
            ArrayList<String> listOfDescriptionAndFromToDate = splitDescriptionAndFromToDate(
                    listOfDescriptionAndDetails.get(1));
            return new AddTaskCommand(new Event(
                    listOfDescriptionAndFromToDate.get(0),
                    listOfDescriptionAndFromToDate.get(1),
                    listOfDescriptionAndFromToDate.get(2)));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(listOfDescriptionAndDetails.get(1)));
        case FIND:
            return new FindCommand(listOfDescriptionAndDetails.get(1).split(" "));
        default:
            throw new UnknownCommandException();
        }

    }

    private static ArrayList<String> splitDescriptionAndDetails(String userInput) {
        return new ArrayList<String>(List.of(userInput.split(" ", 2)));
    }

    private static ArrayList<String> splitDescriptionAndByDate(String input) {
        return new ArrayList<String>(List.of(input.split(" /by ")));
    }

    private static ArrayList<String> splitDescriptionAndFromToDate(String input) {
        String[] listOfDescriptionAndDetails = input.split(" /from ");
        String[] listOfFromToDate = listOfDescriptionAndDetails[1].split(" /to ");

        ArrayList<String> result = new ArrayList<String>();
        result.add(listOfDescriptionAndDetails[0]);
        result.add(listOfFromToDate[0]);
        result.add(listOfFromToDate[1]);

        return result;
    }

    /**
     * Checks if the given arguments are valid for its task to be created.
     *
     * @param commandType is the type of the task
     * @param input is the full user input string
     * @throws BerryException if the given string has missing or incomplete arguments
     */
    private static void validateCommand(CommandType commandType, String input) throws BerryException {
        String command = input.split(" ")[0];

        if (!(commandType == CommandType.TODO
                || commandType == CommandType.DEADLINE
                || commandType == CommandType.EVENT
                || commandType == CommandType.FIND)) {
            return;
        }

        switch(commandType) {
        case TODO:
            if (input.split("todo")[1].isBlank()) {
                throw new EmptyDescriptionException(command);
            }
            break;
        case DEADLINE:
            if (!input.contains("/by")) {
                throw new MissingClauseException("by");
            } else if (input.endsWith("/by") || input.split("/by")[1].isBlank()) {
                throw new EmptyClauseException("by");
            }
            break;
        case EVENT:
            if (!input.contains("/from")) {
                throw new MissingClauseException("from");
            } else if (!input.contains("/to")) {
                throw new MissingClauseException("to");
            } else if ((input.split("/from")[0].split("event")[1]).isBlank()) {
                throw new EmptyDescriptionException(command);
            } else if (input.endsWith("/to") || input.split("/to")[1].isBlank()) {
                throw new EmptyClauseException("to");
            } else if (input.endsWith("/from") || input.split("/to")[0].split("/from")[1].isBlank()) {
                throw new EmptyClauseException("from");
            }
            break;
        case FIND:
            if (input.endsWith("find") || input.split("find")[1].trim().equals("")) {
                throw new BerryException("Please key in a keyword for me to find :<");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
