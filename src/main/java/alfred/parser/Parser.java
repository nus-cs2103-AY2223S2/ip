package alfred.parser;

import alfred.command.*;
import alfred.exceptions.AlfredException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

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
