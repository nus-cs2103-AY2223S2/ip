package parser;

import command.Command;
import command.MarkCommand;
import command.UnmarkCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class UnmarkParser implements Parser {
    @Override
    public Command parse(String requestContent) throws MissingArgumentException, InvalidArgumentException {
        if (requestContent.trim().equals("")) {
            throw new MissingArgumentException("The index to be marked uncompleted cannot be empty.");
        }
        try {
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException error) {
            throw new InvalidArgumentException("The index to be marked uncompleted must be an integer.");
        }
    }
}
