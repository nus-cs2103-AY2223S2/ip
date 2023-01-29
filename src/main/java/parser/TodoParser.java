package parser;

import command.Command;
import command.TodoCommand;
import dukeexeption.MissingArgumentException;

/**
 * Parser that handles command that starts with todo keyword.
 */
public class TodoParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException {
        if ("".equals(requestContent.trim())) {
            throw new MissingArgumentException("The description of a todo cannot be empty. " + "Format: todo [task]");
        }
        String task = requestContent.trim();
        return new TodoCommand(task);
    }
}
