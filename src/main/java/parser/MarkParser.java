package parser;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.MarkCommand;

/**
 * Parser that handles command that starts with mark keyword.
 */
public class MarkParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if ("".equals(requestContent.trim())) {
            throw new MissingArgumentException(
                    "The index to be marked completed cannot be empty. " + "Format: mark [index]");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be marked completed must be an integer.");
        }
    }
}
