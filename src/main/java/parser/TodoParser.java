package parser;

import command.Command;
import command.TodoCommand;
import dukeexeption.MissingArgumentException;

public class TodoParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException {
        if (requestContent.trim().equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }
        String task = requestContent.trim();
        return new TodoCommand(task);
    }
}
