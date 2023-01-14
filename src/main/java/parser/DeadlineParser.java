package parser;

import command.Command;
import command.DeadlineCommand;

public class DeadlineParser implements Parser{
    @Override
    public Command parse(String requestContent) {
        String task = requestContent.split(" /by ", 2)[0];
        String deadline = requestContent.split(" /by ", 2)[1];
        return new DeadlineCommand(task, deadline);
    }
}
