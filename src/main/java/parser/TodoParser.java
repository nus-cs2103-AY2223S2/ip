package parser;

import command.Command;
import command.TodoCommand;

public class TodoParser implements Parser{
    @Override
    public Command parse(String requestContent) {
        String task = requestContent;
        return new TodoCommand(task);
    }
}
