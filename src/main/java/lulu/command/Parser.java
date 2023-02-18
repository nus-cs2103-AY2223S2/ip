package lulu.command;

import lulu.exception.InvalidCommandException;
import lulu.exception.LuluException;

/**
 * This class makes sense of the user's input by parsing it as valid Commands to be executed.
 */
public class Parser {
    /**
     * The available valid commands
     */
    private enum Commands {
        LIST, MARK, UNMARK, DELETE, DEADLINE, EVENT, TODO, BYE, FIND, UPDATE
    }

    /**
     * This method parses the string of text by the user into a given command.
     * If no command is found in the text, an InvalidCommandException will be thrown.
     *
     * @param inputCommand the string provided by the user
     * @return the command generated from the users' text
     * @throws LuluException when command is invalid or command is valid with subsequent invalid inputs
     */
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
            case UPDATE:
                return new UpdateCommand(restOfCommand);
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }
}
