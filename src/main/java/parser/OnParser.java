package parser;

import command.Command;
import command.DeadlineCommand;
import command.OnCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class OnParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        String dateString = requestContent.trim();
        if (dateString.equals("")) {
            throw new MissingArgumentException("The date to be queried cannot be empty. " +
                    "Format: on [YYYY-MM-DD]");
        }
        try {
            LocalDate queryDate = LocalDate.parse(requestContent.trim());
            return new OnCommand(queryDate);
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException("Query date format should be in the format YYYY-MM-DD (e.g. 2007-12-03)");
        }
    }
}
