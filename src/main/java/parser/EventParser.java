package parser;

import command.Command;
import command.EventCommand;
import dukeexeption.MissingArgumentException;

public class EventParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException {
        String[] splitWithFrom = requestContent.split(" /from ", 2);
        String task = splitWithFrom[0].trim();
        if (task.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitWithFrom.length != 2) {
            throw new MissingArgumentException("The start time cannot be empty.");
        }
        String[] splitWithTo = splitWithFrom[1].split(" /to ", 2);
        String startTime = splitWithTo[0].trim();
        if (startTime.equals("")) {
            throw new MissingArgumentException("The start time cannot be empty.");
        }else if (
            splitWithTo.length != 2 ||
            splitWithTo[1].trim().equals("")
        ) {
            throw new MissingArgumentException("The end time cannot be empty.");
        }
        String endTime = splitWithTo[1].trim();
        return new EventCommand(task, startTime, endTime);
    }
}
