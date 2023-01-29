package lulu.command;

import lulu.exception.InvalidCommandException;
import lulu.exception.LuluException;

public class Parser {
    private enum Commands {
        LIST, MARK, UNMARK, DELETE, DEADLINE, EVENT, TODO, BYE, FIND
    }

    public static Command parse(String inputCommand) throws LuluException {
        int size = inputCommand.length();
        int i = inputCommand.indexOf(" ");
        try {
            Commands cmd = Commands.valueOf(
                    i == -1
                            ? inputCommand.toUpperCase()
                            : inputCommand.substring(0, i).toUpperCase());

            String restOfCommand =
                    i == -1
                            ? ""
                            : inputCommand.substring(i + 1, size);

            switch (cmd) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(restOfCommand);
            case UNMARK:
                return new UnmarkCommand(restOfCommand);
            case DELETE:
                return new DeleteCommand(restOfCommand);
            case DEADLINE:
                return new DeadlineCommand(restOfCommand);
            case EVENT:
                return new EventCommand(restOfCommand);
            case TODO:
                return new TodoCommand(restOfCommand);
            case BYE:
                return new ByeCommand();
            case FIND:
                return new FindCommand(restOfCommand);
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }
}
