package parser;

import command.Command;
import command.EventCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        String[] splitWithFrom = requestContent.split(" /from ", 2);
        String task = splitWithFrom[0].trim();
        if (task.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty. " +
                    "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        } else if (splitWithFrom.length != 2) {
            throw new MissingArgumentException("The start date cannot be empty. " +
                    "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        String[] splitWithTo = splitWithFrom[1].split(" /to ", 2);
        LocalDate startTime;
        try {
            startTime = LocalDate.parse(splitWithTo[0].trim());
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException("Start date format should be in the format " +
                    "YYYY-MM-DD (e.g. 2007-12-03)");
        }
        if (startTime.equals("")) {
            throw new MissingArgumentException("The start date cannot be empty. " +
                    "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }else if (
            splitWithTo.length != 2 ||
            splitWithTo[1].trim().equals("")
        ) {
            throw new MissingArgumentException("The end date cannot be empty. " +
                    "Format: event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        LocalDate endTime;
        try {
            endTime = LocalDate.parse(splitWithTo[1].trim());
        } catch (DateTimeParseException error) {
            throw new InvalidArgumentException("End date format should be in the format " +
                    "YYYY-MM-DD (e.g. 2007-12-03)");
        }
        return new EventCommand(task, startTime, endTime);
    }
}
