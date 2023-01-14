package parser;

import command.Command;
import command.EventCommand;

public class EventParser implements Parser{
    @Override
    public Command parse(String requestContent) {
        String[] splittedContent = requestContent.split(" /from ", 2);
        String task = splittedContent[0];
        String startTime = splittedContent[1].split(" /to ", 2)[0];
        String endTime = splittedContent[1].split(" /to ", 2)[1];
        return new EventCommand(task, startTime, endTime);
    }
}
