package parser;

import command.Command;
import command.MarkCommand;

public class MarkParser implements Parser{
    @Override
    public Command parse(String requestContent) {
        int index = Integer.parseInt(requestContent) - 1;
        return new MarkCommand(index);
    }
}
