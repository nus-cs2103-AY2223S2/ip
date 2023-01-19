package jarvis;

import jarvis.command.*;
import jarvis.exception.InvalidActionException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Parser class for user commands.
 */
public class Parser {

    /**
     * Parses a command string.
     *
     * @param commandLine User input string.
     * @return Parsed command.
     * @throws InvalidActionException If command keyword is invalid.
     */
    public static Command parse(String commandLine) throws InvalidActionException {
        return Parser.parse(new Scanner(commandLine));
    }

    /**
     * Parses a command from a Scanner.
     *
     * @param scanner Scanner containing user input.
     * @return Parsed command.
     * @throws InvalidActionException If command keyword is invalid.
     */
    public static Command parse(Scanner scanner) throws InvalidActionException {
        Command.Action action = Command.Action.fromString(scanner.hasNext() ? scanner.next() : null);
        String body = Objects.toString(scanner.skip("\\s*").findInLine("[^/]*"), "").trim();
        List<Command> subCommands = new LinkedList<>();

        scanner.skip("[^/]*").useDelimiter("\\s*/\\s*");
        while (scanner.skip("\\s*").hasNext()) {
            subCommands.add(Parser.parse(new Scanner(scanner.next())));
        }
        scanner.close();

        switch (action) {
        case BYE:
            return new ByeCommand();
        case LIST:
        case FIND:
            return new ListCommand(action, body, subCommands);
        case MARK_DONE: // Fallthrough
        case MARK_UNDONE:
            return new MarkCommand(action, body);
        case DELETE_TASK:
            return new DeleteCommand(action, body);
        case CREATE_TODO:
            return new ToDoCommand(action, body);
        case CREATE_DEADLINE:
            return new DeadlineCommand(action, body, subCommands);
        case CREATE_EVENT:
            return new EventCommand(action, body, subCommands);
        case DEADLINE_BY: // Fallthrough
        case EVENT_FROM: // Fallthrough
        case EVENT_TO:
            return new SubCommand(action, body, subCommands);
        default:
            return new UnknownCommand();
        }
    }
}
