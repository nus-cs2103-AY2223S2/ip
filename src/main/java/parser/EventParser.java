package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.EventCommand;

/**
 * Parser that handles command that starts with event keyword.
 */
public class EventParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        String[] splitWithFrom = requestContent.split(" /from ", 2);
        String task = splitWithFrom[0].trim();
        if ("".equals(task)) {
            throw new MissingArgumentException("The description of an event cannot be empty. "
                    + "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        } else if (splitWithFrom.length != 2) {
            throw new MissingArgumentException("The start date cannot be empty. "
                    + "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        String[] splitWithTo = splitWithFrom[1].split(" /to ", 2);

        LocalDate startTime;
        if (splitWithTo[0].trim().equals("")) {
            throw new MissingArgumentException("The start date cannot be empty. "
                    + "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        try {
            startTime = LocalDate.parse(splitWithTo[0].trim());
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException("Start date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }

        if (splitWithTo.length != 2 || "".equals(splitWithTo[1].trim())) {
            throw new MissingArgumentException("The end date cannot be empty. "
                    + "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        LocalDate endTime;
        try {
            endTime = LocalDate.parse(splitWithTo[1].trim());
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException("End date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }
        return new EventCommand(task, startTime, endTime);
    }
}
