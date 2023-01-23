package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;

public class Parser {

    public static Command parse(String rawCommand) throws DukeException {
        String[] rawSplit = rawCommand.split(" ", 2);
        String commandName = rawSplit[0];
        String argString = null;
        if (rawSplit.length == 2) {
            argString = rawSplit[1];
        }

        Command command = null;
        int index = -1;
        String[] args = null;

        switch (commandName) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            // TODO: throw exception in MarkCommand if mark index out of bounds
            args = Parser.getArgs(commandName, argString, 1);
            index = Integer.parseInt(args[0]) - 1;
            command = new MarkCommand(index);
            break;
        case "unmark":
            // TODO: throw exception in UnmarkCommand if mark index out of bounds
            args = Parser.getArgs(commandName, argString, 1);
            index = Integer.parseInt(args[0]) - 1;
            command = new UnmarkCommand(index);
            break;
        case "todo":
            args = Parser.getArgs(commandName, argString, 1, new String[] {});
            command = new ToDoCommand(args[0]);
            break;
        case "deadline":
            args = Parser.getArgs(commandName, argString, 2, new String[] { "/by" });
            command = new DeadlineCommand(args[0], args[1]);
            break;
        case "event":
            args = Parser.getArgs(commandName, argString, 3, new String[] { "/from", "/to" });
            command = new EventCommand(args[0], args[1], args[2]);
            break;
        default:
            throw new UnknownCommandException(commandName);
        }
        return command;
    }

    public static String[] getArgs(String commandName, String argString, int numArgs)
            throws InvalidArgumentException {
        String[] args = argString.split(" ");
        if (args.length != numArgs) {
            throw new InvalidArgumentException(commandName);
        } else {
            return args;
        }
    }

    public static String[] getArgs(String commandName, String argString, int numArgs, String[] flags)
            throws InvalidArgumentException {
        String[] args = new String[numArgs];
        if (numArgs - 1 != flags.length) {
            // function was called incorrectly:
            // numArgs and given flags do not match
            throw new InvalidArgumentException(commandName);
        }

        for (int i = 0; i < flags.length; i++) {
            String[] split = argString.split(flags[i], 2);

            if (split.length != 2) {
                // flag was not found -- invalid input
                throw new InvalidArgumentException(commandName);
            }

            args[i] = split[0].trim();
            argString = split[1];
        }
        args[numArgs - 1] = argString.trim();
        return args;
    }
}
