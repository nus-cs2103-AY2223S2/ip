package alfred.parser;

import java.time.LocalDate;

import alfred.command.Command;
import alfred.command.DeadlineCommand;
import alfred.command.DeleteCommand;
import alfred.command.EventCommand;
import alfred.command.ExitCommand;
import alfred.command.FindCommand;
import alfred.command.HelpCommand;
import alfred.command.ListCommand;
import alfred.command.ListDateCommand;
import alfred.command.MarkCommand;
import alfred.command.ToDoCommand;
import alfred.command.UnmarkCommand;
import alfred.exceptions.AlfredException;
import alfred.exceptions.InvalidCommandException;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    private final DateTimeParser dateTimeParser = new DateTimeParser();
    /**
     * Translates the user command into the respective command.
     * @param fullCommand The full command given by the user.
     * @return The command that is parsed out from the full command.
     * @throws AlfredException The error thrown due to a failure to parse date and time.
     */
    public Command parse(String fullCommand) throws AlfredException {
        String[] commandArr = fullCommand.split(" ");
        int commandSize = commandArr.length;

        switch (commandSize) {
        case (1):
            return getOneWordCommand(commandArr);
        case (2):
            return getTwoWordCommand(commandArr);
        default: // more than 3 word
            return getLongCommand(fullCommand);
        }
    }

    private Command getOneWordCommand(String[] commandArr) throws InvalidCommandException {
        String command = commandArr[0];
        switch (command) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("help"):
            return new HelpCommand();
        default:
            throw new InvalidCommandException(command);
        }
    }

    private Command getTwoWordCommand(String[] commandArr) throws AlfredException {
        String command = commandArr[0];
        String secondWord = commandArr[1];
        switch (command) {
        case ("mark"):
            return new MarkCommand(secondWord);
        case ("unmark"):
            return new UnmarkCommand(secondWord);
        case ("delete"):
            return new DeleteCommand(secondWord);
        case ("list"):
            LocalDate date = dateTimeParser.parseIntoLocalDate(secondWord);
            return new ListDateCommand(date);
        case ("todo"):
            return new ToDoCommand(secondWord);
        case ("deadline"):
            return new DeadlineCommand(secondWord);
        case ("event"):
            return new EventCommand(secondWord);
        case ("find"):
            return new FindCommand(secondWord);
        default:
            throw new InvalidCommandException(command);
        }
    }

    private Command getLongCommand(String fullCommand) throws AlfredException {
        String[] fullCommandArr = fullCommand.split(" ", 2);
        int fullCommandSize = fullCommandArr.length;
        String command = fullCommandArr[0];

        switch (fullCommandSize) {
        case (1):
            return getOneWordCommand(fullCommandArr);
        case (2):
            return getTwoWordCommand(fullCommandArr);
        default:
            // this line should never happen because max size is 2
            throw new InvalidCommandException(command);
        }
    }
}
