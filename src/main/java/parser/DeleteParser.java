package parser;

import command.Command;
import command.DeleteCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class DeleteParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if (requestContent.trim().equals("")) {
            throw new MissingArgumentException("The index to be deleted cannot be empty.");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be deleted must be an integer.");
        }
    }
}
