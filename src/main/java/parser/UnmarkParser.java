package parser;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.UnmarkCommand;


/**
 * Parser that handles command that starts with unmark keyword.
 */
public class UnmarkParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if ("".equals(requestContent.trim())) {
            throw new MissingArgumentException("The index to be marked uncompleted cannot be empty. "
                    + "Format: unmark [index]");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be marked uncompleted must be an integer.");
        }
    }
}
