package jarvis.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import jarvis.command.ByeCommand;
import jarvis.command.Command;
import jarvis.command.DeadlineCommand;
import jarvis.command.DeleteCommand;
import jarvis.command.EventCommand;
import jarvis.command.IntroCommand;
import jarvis.command.ListCommand;
import jarvis.command.MarkCommand;
import jarvis.command.TimedCommand;
import jarvis.command.ToDoCommand;
import jarvis.command.UnknownCommand;
import jarvis.command.subcommand.ByCommand;
import jarvis.command.subcommand.DurationCommand;
import jarvis.command.subcommand.FromCommand;
import jarvis.command.subcommand.ToCommand;
import jarvis.exception.command.InvalidActionException;


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
        assert commandLine != null;
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
        assert scanner != null;
        Command.Action action = Command.Action.fromString(scanner.hasNext() ? scanner.next() : null);
        String body = Objects.toString(scanner.skip("\\s*").findInLine("[^/]*"), "").trim();
        List<Command> subCommands = new LinkedList<>();

        scanner.skip("[^/]*").useDelimiter("\\s*/\\s*");
        while (scanner.skip("\\s*").hasNext()) {
            subCommands.add(Parser.parse(new Scanner(scanner.next())));
        }
        scanner.close();

        switch (action) {
        case INTRO:
            return new IntroCommand();
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand(body, subCommands);
        case MARK_DONE:
            return new MarkCommand(body, true);
        case MARK_UNDONE:
            return new MarkCommand(body, false);
        case DELETE_TASK:
            return new DeleteCommand(body);
        case CREATE_TODO:
            return new ToDoCommand(body);
        case CREATE_DEADLINE:
            return new DeadlineCommand(body, subCommands);
        case CREATE_EVENT:
            return new EventCommand(body, subCommands);
        case CREATE_TIMED:
            return new TimedCommand(body, subCommands);
        case DEADLINE_BY:
            return new ByCommand(body);
        case EVENT_FROM:
            return new FromCommand(body);
        case EVENT_TO:
            return new ToCommand(body);
        case DURATION_DAYS:
        case DURATION_HOURS:
        case DURATION_MINUTES:
            return new DurationCommand(action, body);
        default:
            return new UnknownCommand();
        }
    }
}
