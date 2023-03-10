package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.commands.ByeCommand;
import duke.commands.CheckDuplicateCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.SearchCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

/**
 * Parses the input command from user to the Duke chat-bot.
 */
public class Parser {
    protected static final String INPUT_DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String LIST = "list";

    private Command parseAddCommand(String inputCommand, String userInput) {
        Command command;
        switch (inputCommand) {
        case TodoCommand.COMMAND:
            command = parseTodoCommand(userInput);
            break;
        case DeadlineCommand.COMMAND:
            command = parseDeadlineCommand(userInput);
            break;
        case EventCommand.COMMAND:
            command = parseEventCommand(userInput);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    private Command parseTodoCommand(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length <= 1 || tokens[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tokens[1] = tokens[1].trim();
        if (tokens[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(tokens);
    }

    private Command parseDeadlineCommand(String userInput) {
        String[] tokens = new String[3];
        String[] splitDate = userInput.split(" /by ");
        if (splitDate.length <= 1) {
            throw new DukeException("Invalid format of deadline command.");
        }
        String[] temp = splitDate[0].split(" ", 2);
        if (temp.length <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        tokens[0] = temp[0];
        tokens[1] = temp[1];
        tokens[2] = splitDate[1];
        return new DeadlineCommand(tokens);
    }

    private Command parseEventCommand(String userInput) {
        String[] tokens = new String[4];
        String[] splitStartDate = userInput.split(" /from ");
        if (splitStartDate.length <= 1) {
            throw new DukeException("Invalid format of event command.");
        }
        String[] splitStartAndEndDate = splitStartDate[1].split(" /to ");
        if (splitStartAndEndDate.length <= 1) {
            throw new DukeException("End date/time of event unspecified.");
        }
        tokens[2] = splitStartAndEndDate[0];
        tokens[3] = splitStartAndEndDate[1];
        String[] commandWithDescription = splitStartDate[0].split(" ", 2);
        if (commandWithDescription.length <= 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        tokens[0] = commandWithDescription[0];
        tokens[1] = commandWithDescription[1];
        return new EventCommand(tokens);
    }

    private Command parseSearchCommand(String userInput) {
        return new SearchCommand(userInput.split(" ", 2));
    }

    /**
     * Parses the input command entered by user.
     *
     * @param userInput Input command of user.
     * @return The Command object that contains the argument information.
     */
    public Command parseInput(String userInput) {
        String inputCommand = userInput.split(" ", 2)[0];
        Command command;
        if (inputCommand.equals(TodoCommand.COMMAND) || inputCommand.equals(DeadlineCommand.COMMAND)
                || inputCommand.equals(EventCommand.COMMAND)) {
            return parseAddCommand(inputCommand, userInput);
        }
        switch(inputCommand) {
        case DeleteCommand.COMMAND:
            command = new DeleteCommand(userInput.split(" "));
            break;
        case MarkCommand.COMMAND:
            command = new MarkCommand(userInput.split(" "));
            break;
        case UnmarkCommand.COMMAND:
            command = new UnmarkCommand(userInput.split(" "));
            break;
        case ByeCommand.COMMAND:
            command = new ByeCommand();
            break;
        case ListCommand.COMMAND:
            command = new ListCommand();
            break;
        case CheckDuplicateCommand.COMMAND:
            command = new CheckDuplicateCommand();
            break;
        case SearchCommand.COMMAND:
            command = parseSearchCommand(userInput);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    /**
     * Parses date and time in String representation to LocalDateTime object.
     *
     * @param s String representation of date and time.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
    }
}
