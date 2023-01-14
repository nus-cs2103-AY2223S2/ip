package parser;

import command.Command;
import command.UnmarkCommand;

public class UnmarkParser implements Parser{
    @Override
    public Command parse(String requestContent) {
        int index = Integer.parseInt(requestContent) - 1;
        return new UnmarkCommand(index);
    }
}
