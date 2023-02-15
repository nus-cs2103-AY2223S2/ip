package spongebob.parser;

import spongebob.command.AddCommand;
import spongebob.command.Command;
import spongebob.command.DeleteCommand;
import spongebob.command.ExitCommand;
import spongebob.command.FindCommand;
import spongebob.command.ListCommand;
import spongebob.command.MarkCommand;
import spongebob.command.UnmarkCommand;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobUnknownCommandException;

/**
 * Parser to parse user input.
 */
public class Parser {
    /**
     * Parses the user input command and response it correspondingly.
     *
     * @param input user input command
     * @return corresponding command given a specific user input.
     * @throws SpongebobUnknownCommandException indicate that an unknown command has been passed.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws SpongebobEventOverlapException indicate there are overlapping tasks exist.
     */
    public static Command parse(String input) throws SpongebobUnknownCommandException,
            SpongebobEmptyArgumentException, SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        String[] fullCommand = input.split(" ", 2);
        String command = fullCommand[0];
        return getCommand(fullCommand, command);
    }

    private static Command getCommand(String[] split, String cmd) throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobUnknownCommandException, SpongebobEventOverlapException {
        Command c;
        switch (cmd.toLowerCase()) {
        case "exit":
            c = new ExitCommand(split);
            break;
        case "list":
            c = new ListCommand(split);
            break;
        case "delete":
            c = new DeleteCommand(split);
            break;
        case "mark":
            c = new MarkCommand(split);
            break;
        case "unmark":
            c = new UnmarkCommand(split);
            break;
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(split);
            break;
        case "find":
            c = new FindCommand(split);
            break;
        default:
            throw new SpongebobUnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }
}
