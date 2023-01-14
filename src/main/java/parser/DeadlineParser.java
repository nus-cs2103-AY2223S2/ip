package parser;

import command.Command;
import command.DeadlineCommand;
import dukeexeption.MissingArgumentException;

public class DeadlineParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String task = splitWithBy[0].trim();
        if (task.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (
            splitWithBy.length != 2 ||
            splitWithBy[1].trim().equals("")
        ) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }
        String deadline = splitWithBy[1].trim();
        return new DeadlineCommand(task, deadline);
    }
}
