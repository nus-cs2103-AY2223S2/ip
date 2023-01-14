package parser;

import command.Command;
import command.MarkCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class MarkParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if (requestContent.trim().equals("")) {
            throw new MissingArgumentException("The index to be marked completed cannot be empty.");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be marked completed must be an integer.");
        }
    }
}
