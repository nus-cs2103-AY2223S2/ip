package parser;

import command.Command;
import command.ListCommand;

/**
 * Parser that handles command that starts with list keyword.
 */
public class ListParser implements Parser {
    @Override
    public Command parse(String requestContent) {
        return new ListCommand();
    }
}
