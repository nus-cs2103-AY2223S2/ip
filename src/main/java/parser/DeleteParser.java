package parser;

import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

import command.Command;
import command.DeleteCommand;

/**
 * Parser that handles command that starts with delete keyword.
 */
public class DeleteParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if ("".equals(requestContent.trim())) {
            throw new MissingArgumentException("The index to be deleted cannot be empty. " + "Format: delete [index]");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be deleted must be an integer.");
        }
    }
}
