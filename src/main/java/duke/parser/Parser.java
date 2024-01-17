package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;

/**
 * The Parser class parses the user input.
 *
 * @author Chia Jeremy
 */
public class Parser {

    private enum CommandCode {
        TODO, DEADLINE, EVENT,
        DELETE,
        MARK, UNMARK,
        FIND,
        LIST,
        HELP,
        EXIT,
        INVALID
    }

    /**
     * Returns the command code of the user input.
     *
     * @param input the user input
     * @return the command code of the input
     */
    public static CommandCode getCommand(String input) {
        String command = input.split(" ", 2)[0].toLowerCase();
        switch (command) {
        case "todo":
            return CommandCode.TODO;
        case "deadline":
            return CommandCode.DEADLINE;
        case "event":
            return CommandCode.EVENT;
        case "delete":
            return CommandCode.DELETE;
        case "mark":
            return CommandCode.MARK;
        case "unmark":
            return CommandCode.UNMARK;
        case "find":
            return CommandCode.FIND;
        case "list":
            return CommandCode.LIST;
        case "help":
            return CommandCode.HELP;
        case "bye":
            return CommandCode.EXIT;
        default:
            return CommandCode.INVALID;
        }
    }

    /**
     * Returns the command based on the user input.
     *
     * @param input the user input
     * @return the command based on the input
     */
    public Command parseCommand(String input) {
        CommandCode commandCode = getCommand(input);
        switch (commandCode) {
        case TODO:
            return prepareTodo(input);
        case DEADLINE:
            return prepareDeadline(input);
        case EVENT:
            return prepareEvent(input);
        case DELETE:
            return prepareDelete(input);
        case MARK:
            return prepareMark(input);
        case UNMARK:
            return prepareUnmark(input);
        case FIND:
            return prepareFind(input);
        case LIST:
            return prepareList();
        case HELP:
            return prepareHelp();
        case EXIT:
            return prepareExit();
        default:
            return prepareInvalid();
        }
    }

    private Command prepareTodo(String input) {
        try {
            // Extract arguments
            int todoStartIndex = 4;
            String args = input.substring(todoStartIndex).trim();
            checkParamExists(!args.isBlank(), "Description of a TODO command cannot be empty.");
            return new AddCommand(args);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    private Command prepareDeadline(String input) {
        try {
            // Extract arguments
            int deadlineStartIndex = 8;
            String args = input.substring(deadlineStartIndex).trim();

            // Check the required keyword exists
            String by = "/by";
            checkParamExists(args.contains(by), "Parameter /by in the DEADLINE command is missing.");

            // Check the method parameters exists
            int byIndex = args.indexOf(by);
            String descr = args.substring(0, byIndex).trim();
            String dateTime = args.substring(byIndex + by.length()).trim();
            checkParamExists(!descr.isBlank(), "Description of a DEADLINE command cannot be empty.");
            checkParamExists(!dateTime.isBlank(), "Datetime of a DEADLINE command cannot be empty.");

            // Format date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dt = LocalDateTime.parse(dateTime, formatter);

            // Instantiate command
            return new AddCommand(descr, dt);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    private Command prepareEvent(String input) {
        try {
            // Extract arguments
            int eventStartIndex = 5;
            String args = input.substring(eventStartIndex).trim();

            // Check the required keyword exists
            String from = "/from";
            String to = "/to";
            checkParamExists(args.contains(from), "Parameter /from in the EVENT command is missing.");
            checkParamExists(args.contains(to), "Parameter /to in the EVENT command is missing.");

            // Check the method parameters exists
            int fromIndex = args.indexOf(from);
            int toIndex = args.indexOf(to);
            String descr = args.substring(0, fromIndex).trim();
            String startDateTime = args.substring(fromIndex + from.length(), toIndex).trim();
            String endDateTime = args.substring(toIndex + to.length()).trim();
            checkParamExists(!descr.isBlank(), "Description of a EVENT command cannot be empty.");
            checkParamExists(!startDateTime.isBlank(), "Start datetime of a EVENT command cannot be empty.");
            checkParamExists(!endDateTime.isBlank(), "End datetime of a EVENT command cannot be empty.");

            // Format date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDt = LocalDateTime.parse(startDateTime, formatter);
            LocalDateTime endDt = LocalDateTime.parse(endDateTime, formatter);

            // Instantiate command
            return new AddCommand(descr, startDt, endDt);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    private Command prepareDelete(String input) {
        // Extract arguments
        int deleteStartIndex = 6;
        String args = input.substring(deleteStartIndex).trim();
        int index = Integer.parseInt(args);
        return new DeleteCommand(index - 1);
    }

    private Command prepareMark(String input) {
        // Extract arguments
        int markStartIndex = 4;
        String args = input.substring(markStartIndex).trim();
        int index = Integer.parseInt(args);
        return new MarkCommand(index - 1);
    }

    private Command prepareUnmark(String input) {
        // Extract arguments
        int unmarkStartIndex = 6;
        String args = input.substring(unmarkStartIndex).trim();
        int index = Integer.parseInt(args);
        return new UnmarkCommand(index - 1);
    }

    private Command prepareFind(String input) {
        // Extract arguments
        int findStartIndex = 4;
        String args = input.substring(findStartIndex).trim();
        return new FindCommand(args);
    }

    private Command prepareList() {
        return new ListCommand();
    }

    private Command prepareHelp() {
        return new HelpCommand();
    }

    private Command prepareExit() {
        return new ExitCommand();
    }

    private Command prepareInvalid() {
        return new InvalidCommand();
    }

    private void checkParamExists(Boolean isExists, String errorMessage) throws DukeException {
        if (!isExists) {
            throw new DukeException(errorMessage);
        }
    }
}
