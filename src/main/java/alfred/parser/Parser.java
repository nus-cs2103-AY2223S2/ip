package alfred.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import alfred.command.AddCommand;
import alfred.command.Command;
import alfred.command.DeleteCommand;
import alfred.command.ExitCommand;
import alfred.command.FindCommand;
import alfred.command.ListCommand;
import alfred.command.ListDateCommand;
import alfred.command.MarkCommand;
import alfred.command.UnmarkCommand;
import alfred.exceptions.AlfredException;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Translates the user command into the respective command.
     * @param fullCommand The full command given by the user.
     * @return The command that is parsed out from the full command.
     * @throws AlfredException The error thrown due to a failure to parse date and time.
     */
    public Command parse(String fullCommand) throws AlfredException {
        String[] lineArr = fullCommand.split(" ");
        String command = lineArr[0];
        if (command.equals("bye") && lineArr.length == 1) { // So we can still add taskNames that start with bye
            return new ExitCommand();
        } else if (command.equals("list") && lineArr.length == 1) {
            return new ListCommand();
        } else if (command.equals("mark") && lineArr.length == 2) {
            return new MarkCommand(lineArr[1]);
        } else if (command.equals("unmark") && lineArr.length == 2) {
            return new UnmarkCommand(lineArr[1]);
        } else if (command.equals("delete") && lineArr.length == 2) {
            return new DeleteCommand(lineArr[1]);
        } else if (command.equals("find")) { // what if there's task called "find ball?"
            return new FindCommand(lineArr[1]);
        } else if (command.equals("list") && lineArr.length == 2) {
            String second = lineArr[1];
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate date = LocalDate.parse(second, format);
                return new ListDateCommand(date);
            } catch (DateTimeParseException e) {
                throw new AlfredException("The date format should be given as dd/mm/yyyy\n");
            }
        } else {
            return new AddCommand(fullCommand);
        }

    }
}
