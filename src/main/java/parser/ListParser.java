package parser;

import command.Command;
import command.ListCommand;

public class ListParser implements Parser {
    @Override
    public Command parse(String requestContent) {
        return new ListCommand();
    }
}
