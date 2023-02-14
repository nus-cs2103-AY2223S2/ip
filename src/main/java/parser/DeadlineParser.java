package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.DeadlineCommand;


/**
 * Parser that handles command that starts with deadline keyword.
 */
public class DeadlineParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String task = splitWithBy[0].trim();
        if ("".equals(task)) {
            throw new MissingArgumentException("The description of a deadline cannot be empty. "
                    + "Format: deadline [task] /by [YYYY-MM-DD]");
        } else if (splitWithBy.length != 2 || "".equals(splitWithBy[1].trim())) {
            throw new MissingArgumentException("The deadline cannot be empty. "
                    + "Format: deadline [task] /by [YYYY-MM-DD]");
        }
        LocalDate deadline;
        try {
            deadline = LocalDate.parse(splitWithBy[1].trim());
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException(
                    "Event date format should be in the format YYYY-MM-DD (e.g. 2007-12-03)");
        }
        return new DeadlineCommand(task, deadline);
    }
}
