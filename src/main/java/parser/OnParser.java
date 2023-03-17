package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.OnCommand;

/**
 * Parser that handles command that starts with on keyword.
 */
public class OnParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        String dateString = requestContent.trim();
        if ("".equals(dateString)) {
            throw new MissingArgumentException("The date to be queried cannot be empty. "
                    + "Format: on [YYYY-MM-DD]");
        }
        try {
            LocalDate queryDate = LocalDate.parse(requestContent.trim());
            return new OnCommand(queryDate);
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException(
                    "Query date format should be in the format YYYY-MM-DD (e.g. 2007-12-03)");
        }
    }
}
